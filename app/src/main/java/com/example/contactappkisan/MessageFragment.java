package com.example.contactappkisan;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;


/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {
    Context context;
    RecyclerView recyclerView;
    DBManager dbManager;
    SQLiteDatabase database;
    ArrayList contactDatabaseInfoJava;
    public MessageFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);

        contactDatabaseInfoJava = new ArrayList<ContactDatabaseInfoJava>();
        dbManager = new DBManager(context);

        String[] projections = {"_id", "NAME", "OTP", "TIME"};
        Cursor cursor = dbManager.Query(projections,"", null, null);


        if (cursor.getCount() == 0) {
            //No data..
            Toast.makeText(context, "No data!!", Toast.LENGTH_SHORT).show();
        } else {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex("_id"));
                    String name = cursor.getString(cursor.getColumnIndex("NAME"));
                    String otp = cursor.getString(cursor.getColumnIndex("OTP"));
                    String time = cursor.getString(cursor.getColumnIndex("TIME"));

                    contactDatabaseInfoJava.add(new ContactDatabaseInfoJava(name, time, otp));
                } while (cursor.moveToNext());
            }
        }

        Collections.reverse(contactDatabaseInfoJava);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new SentListAdapter(context, contactDatabaseInfoJava));

        return view;
    }

}
