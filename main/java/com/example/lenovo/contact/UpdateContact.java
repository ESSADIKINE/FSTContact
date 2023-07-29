package com.example.lenovo.contact;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class UpdateContact extends AppCompatActivity {
    private EditText fname,lname,phone,job,email;
    private DbContact dbContact;
    private int id;

    private static final int REQUEST_PHONE_CALL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        fname= findViewById(R.id.txtfname);
        lname= findViewById(R.id.txtlname);
        phone= findViewById(R.id.txtphone);
        job= findViewById(R.id.txtjob);
        email= findViewById(R.id.txtemail);
        ImageButton update = findViewById(R.id.update);


        Intent intent = getIntent();
        id =intent.getIntExtra("id",0);

        dbContact = new DbContact(this);
        final Contact contact = dbContact.getContactById(id);


        fname.setText(contact.getFName());
        lname.setText(contact.getLName());
        phone.setText(contact.getPhone());
        job.setText(contact.getJob());
        email.setText(contact.getEmail());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String finame = fname.getText().toString();
                String laname = lname.getText().toString();
                String tel = phone.getText().toString();
                String jobb = job.getText().toString();
                String mail = email.getText().toString();
                if(fname.length() != 0 && lname.length() != 0 && job.length() != 0 && email.length() != 0) {

                    Contact contact1 = new Contact(id,finame,laname,tel,mail,jobb);

                    dbContact.UpdateContact(contact1);
                    Toast.makeText(UpdateContact.this, "YOUR INFORMATION IS UPDATED WITH SUCCESS", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(UpdateContact.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(UpdateContact.this, "Complete the form", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_delete:
                showAlert();
                break;

            case R.id.btn_call:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone.getText().toString()));


                    if (ContextCompat.checkSelfPermission(UpdateContact.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(UpdateContact.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                    }
                    else
                    {
                        startActivity(intent);
                    }
                 //   return false;
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Select your answer.")
                .setMessage("Are you sure you want to delete this contact?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbContact.DeleteContact(id);
                        Toast.makeText(UpdateContact.this, "Contact deleted !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateContact.this,MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
