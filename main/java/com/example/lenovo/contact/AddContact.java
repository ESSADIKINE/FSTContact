package com.example.lenovo.contact;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
public class AddContact extends AppCompatActivity {
    EditText firstname,lastname,jobb,phone,email;
    ImageButton Submit;
    ImageButton returnButton;
    DbContact dbContact;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        firstname= findViewById(R.id.firstname);
        lastname= findViewById(R.id.lastname);
        jobb= findViewById(R.id.job);
        phone= findViewById(R.id.num);
        email= findViewById(R.id.idemail);
        Submit= findViewById(R.id.save);
        dbContact = new DbContact(this);
        if(Submit!=null) {
            Submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String fname = firstname.getText().toString();
                    String lname = lastname.getText().toString();
                    String job = jobb.getText().toString();
                    String mail = email.getText().toString();
                    String phonee = phone.getText().toString();

                    if (fname.length() != 0 && lname.length() != 0 && job.length() != 0 && email.length() != 0) {
                        Contact contact = new Contact(fname,lname,phonee,mail,job);
                        dbContact.AddContact(contact);

                        Toast.makeText(AddContact.this, "saved ! ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddContact.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(AddContact.this, "Complete the form", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
        returnButton = findViewById(R.id.returnhome);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // close the current activity and return to the previous one
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.reset:
                firstname.getText().clear();
                lastname.getText().clear();
                phone.getText().clear();
                jobb.getText().clear();
                email.getText().clear();
        }

        return super.onOptionsItemSelected(item);
    }

}
