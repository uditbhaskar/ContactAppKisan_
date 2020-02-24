package com.example.contactappkisan;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.InputStream;


public class ContactFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;

    public ContactFragment(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        recyclerView = view.findViewById(R.id.contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        ContactInfoJava[] contactInfoJavas = gson.fromJson(loadJSONFromAsset(),ContactInfoJava[].class);
        recyclerView.setAdapter(new ContactListAdapter(contactInfoJavas, context));

        return view;
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("contactInfo.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
