package com.example.contactappkisan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactDetails extends AppCompatActivity {
    ImageButton imageButton;
    TextView contactName;
    TextView contactNum;
    Toolbar toolbar;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        imageButton=findViewById(R.id.send_button);
        contactName = findViewById(R.id.details_contact_name);
        contactNum = findViewById(R.id.details_contact_num);

        setupToolbar();
        setData();

    }

    private void setData() {
        final Intent intent = getIntent();
        final String name=intent.getStringExtra("name");
        final String phone=intent.getStringExtra("phone");
        if(name!=null){
            contactName.setText(name);
            contactNum.setText(phone);
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 =new Intent(ContactDetails.this,SendMesssageActivity.class);
                intent1.putExtra("name", name);
                intent1.putExtra("phone", phone);
                startActivity(intent1);
            }
        });
    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar_details);
        setSupportActionBar(toolbar);
    }



}
