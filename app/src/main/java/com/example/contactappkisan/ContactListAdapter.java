package com.example.contactappkisan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactListHolder> {


    private ContactInfoJava[] contactData;
    private Context context;

    public ContactListAdapter(ContactInfoJava[] contactData, Context context) {
        this.contactData = contactData;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.contact_item_view, parent, false);
        return new ContactListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactListHolder holder, int position) {

        ContactInfoJava contactInfoJava = contactData[position];
        final String name = contactInfoJava.getName();
        final String number = contactInfoJava.getPhone();
        holder.contactName.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactDetails.class);
                intent.putExtra("name", name);
                intent.putExtra("phone", number);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactData.length;
    }

    public class ContactListHolder extends RecyclerView.ViewHolder {
        TextView contactName;

        public ContactListHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.name_contact);
        }
    }
}
