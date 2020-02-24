package com.example.contactappkisan;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SendMesssageActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView;
    Button otpSendButton;
    String message = "";
    DBManager dbManager;
    Intent intent;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_messsage);
        otp = getRandomNumberString();
        setUpToolbar();
        setUpRandom();
        sendOtp();
        intent=getIntent();

    }

    private void sendOtp() {
        otpSendButton = findViewById(R.id.send_otp_button);
        otpSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MakeNetworkCall().execute(message);
            }
        });


    }

    private void setUpRandom() {
        textView = findViewById(R.id.message_textView);
        message = "Hi. Your OTP is: " + otp;
        textView.setText(message);
    }

    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }


    private void setUpToolbar() {
        toolbar = findViewById(R.id.toolbar_sendMessage);
        setSupportActionBar(toolbar);
    }

    public void setDatabase() {
        String contactName = intent.getStringExtra("name");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String time = formatter.format(date);

        dbManager = new DBManager(this);
        SQLiteDatabase database = dbManager.getWritableDatabase();
        dbManager.insertData(contactName, otp, time, database);

    }

    class MakeNetworkCall extends AsyncTask<String, Void, RESULT> {

        protected RESULT doInBackground(String... sentMessage) {
            try {
                // Construct data
                String phone=intent.getStringExtra("phone");
                String apiKey = "apiKey=" + "4lHbJ5S6TFM-hQSAToXCq28qH7L7YDA8IMHA9ItHDW";
                String message = "&message=" + sentMessage[0];
                String numbers = "&numbers=" +phone;
                String test = "&test=" + "true";

                // Send data
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                String data = apiKey + numbers + message + test;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuffer.append(line);
                }
                rd.close();
                return RESULT.SUCCESS;
            } catch (Exception e) {
                System.out.println("Error SMS " + e);
                return RESULT.ERROR;
            }
        }

        protected void onPostExecute(RESULT result) {
            // TODO: check this.exception
            // TODO: do something with the feed

            if (result == RESULT.ERROR) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "Success", Toast.LENGTH_SHORT).show();
                setDatabase(); // Saving Data in DataBase
                finish();
            }
        }

    }

    enum RESULT {
        SUCCESS,
        ERROR
    }
}
