package com.example.contactappkisan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SentListAdapter extends RecyclerView.Adapter<SentListAdapter.SentListHolder>{

    private Context context;
    private ArrayList<ContactDatabaseInfoJava> contactDatabaseInfo;

    public SentListAdapter(Context context, ArrayList<ContactDatabaseInfoJava> contactDatabaseInfoJava) {
        this.context = context;
        this.contactDatabaseInfo = contactDatabaseInfoJava;
    }


    @NonNull
    @Override
    public SentListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.sent_message_view, parent, false);
        return new SentListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SentListHolder holder, int position) {
        ContactDatabaseInfoJava contactDatabaseInfoJava=contactDatabaseInfo.get(position);
        holder.contact_name.setText("Name : "+ contactDatabaseInfoJava.getName());
        holder.contact_otp.setText("OTP : "+ contactDatabaseInfoJava.getOtp());
        holder.contact_time.setText("Time : "+ contactDatabaseInfoJava.getTime());
    }

    @Override
    public int getItemCount() {
        return contactDatabaseInfo.size();
    }

    public class SentListHolder extends RecyclerView.ViewHolder {
        TextView contact_name;
        TextView contact_otp;
        TextView contact_time;
        public SentListHolder(@NonNull View itemView) {
            super(itemView);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_otp = itemView.findViewById(R.id.contact_otp);
            contact_time = itemView.findViewById(R.id.contact_time);
        }
    }
}
