package com.example.hitesh.contactsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,email,phone;
    Button save;

    Helper helper = new Helper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.edit_name);
        email = (EditText) findViewById(R.id.edit_email);
        phone = (EditText) findViewById(R.id.edit_number);
        save = (Button) findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String na = name.getText().toString();
                String em = email.getText().toString();
                String mob = phone.getText().toString();
                if (na.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                else if (mob.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this, "please enter number", Toast.LENGTH_SHORT).show();
                }
                else if (em.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(em).matches())
                {
                    Toast.makeText(MainActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
                }

                else
                helper.AddContact(new Contact(na,em,mob));
                Intent intent = new Intent(MainActivity.this,ListActivity.class);
                Toast.makeText(MainActivity.this, "Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
