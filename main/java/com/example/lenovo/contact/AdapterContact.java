package com.example.lenovo.contact;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterContact extends ArrayAdapter<Contact> {

    Context ctx;
    int item;

    public AdapterContact(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.ctx= context;
        this.item=resource;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(item,parent,false);

        TextView name = convertView.findViewById(R.id.ContactName);
        TextView phone = convertView.findViewById(R.id.ContactNumber);
        TextView job = convertView.findViewById(R.id.ContactJob);
        TextView email = convertView.findViewById(R.id.ContactEmail);

        Contact contact = getItem(position);
        name.setText(contact.getFName()+ " " +contact.getLName());
        phone.setText(String.valueOf(contact.getPhone()));
        job.setText(String.valueOf(contact.getJob()));
        email.setText(String.valueOf(contact.getEmail()));

        return convertView;
    }
}
