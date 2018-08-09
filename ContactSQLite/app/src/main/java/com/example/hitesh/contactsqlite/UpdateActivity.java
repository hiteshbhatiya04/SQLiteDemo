package com.example.hitesh.contactsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText u_name,u_email,u_phone;
    Button update;
    int id;
    public static int DB_version = 1;
    public static String DB_name = "DB_CONTACT";
    public static String TB_name = "Contact";
    public static String user_id = "id";
    public static String user_name = "name";
    public static String user_phone = "phone";
    public static String user_email = "email";
    Helper helper =new Helper(UpdateActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        u_name = (EditText) findViewById(R.id.update_name);
        u_email = (EditText) findViewById(R.id.update_email);
        u_phone = (EditText) findViewById(R.id.update_number);
        update = (Button) findViewById(R.id.btn_update);

        Intent intent=getIntent();
        id = intent.getIntExtra(user_id, 0);
        u_name.setText(intent.getStringExtra(user_name));
        u_phone.setText(intent.getStringExtra(user_phone));
        u_email.setText(intent.getStringExtra(user_email));




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact=new Contact();

                String uname = u_name.getText().toString();
                String uemail = u_email.getText().toString();
                String umob = u_phone.getText().toString();

                if (uname.trim().isEmpty())
                {
                    Toast.makeText(UpdateActivity.this, "please enter name", Toast.LENGTH_SHORT).show();
                }
                else if (umob.trim().isEmpty())
                {
                    Toast.makeText(UpdateActivity.this, "please enter number", Toast.LENGTH_SHORT).show();
                }
                else if (uemail.trim().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(uemail).matches())
                {
                    Toast.makeText(UpdateActivity.this, "please enter email", Toast.LENGTH_SHORT).show();
                }

                else

                    contact.setId(id);
                contact.setName(uname);
                contact.setPhone(umob);
                contact.setEmail(uemail);
                helper.Update(contact);
                Intent intent1 = new Intent(UpdateActivity.this,ListActivity.class);
                Toast.makeText(UpdateActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent1);
            }
        });

    }
}
