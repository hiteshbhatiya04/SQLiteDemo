package com.example.hitesh.contactsqlite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<HashMap<String,String>>hashMapArrayList =new ArrayList<>();
    List<Contact> list;

    public static int DB_version = 1;
    public static String DB_name = "DB_CONTACT";
    public static String TB_name = "Contact";
    public static String user_id = "id";
    public static String user_name = "name";
    public static String user_phone = "phone";
    public static String user_email = "email";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = (ListView) findViewById(R.id.contact_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { switch(item.getItemId()) {
        case R.id.add_contact:
            Intent i = new Intent(ListActivity.this,MainActivity.class);
            startActivity(i);
            return(true);

    }
        return(super.onOptionsItemSelected(item));
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowContact();
        registerForContextMenu(listView);
    }

    private void ShowContact() {
        hashMapArrayList.clear();
        ListAdapter listAdapter;
        Helper helper =new Helper(getApplicationContext());
        list = (List<Contact>) helper.ReadAllContact();
        for (Contact cn : list) {
            HashMap<String,String>hashMap=new HashMap<>();
            hashMap.put(user_name,cn.getName());
            hashMap.put(user_phone,cn.getPhone());
            hashMap.put(user_email,cn.getEmail());
            hashMapArrayList.add(hashMap);
        }
        listAdapter = new ListAdapter(ListActivity.this,hashMapArrayList,R.layout.list_layout);
        listView.setAdapter(listAdapter);
    }
    public void onCreateContextMenu(ContextMenu contextMenu, View view,ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(contextMenu,view,menuInfo);

        if (view.getId() == R.id.contact_list);
        {
           contextMenu.add(0,1,0,"Update");
           contextMenu.add(0,2,0,"Delete");
        }
    }

    public boolean onContextItemSelected(MenuItem menuItem) {

        AdapterView.AdapterContextMenuInfo contextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();

        final Contact contact = list.get(contextMenuInfo.position);


        switch (menuItem.getItemId()) {
            case 1:
                Intent intent = new Intent(ListActivity.this, UpdateActivity.class);
                intent.putExtra(user_id, contact.getId());
                intent.putExtra(user_name, contact.getName());
                intent.putExtra(user_phone, contact.getPhone());
                intent.putExtra(user_email, contact.getEmail());
                startActivity(intent);
                break;

            case 2:
                final AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                builder.setTitle("Alert Dialog");
                builder.setMessage("Are you sure want to delete ??");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Helper nhelper = new Helper(ListActivity.this);
                        nhelper.delete(contact);
                        ShowContact();
                        Toast.makeText(ListActivity.this, "Deleted Successfull ", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                    }
                });
                builder.show();

        }
        return super.onContextItemSelected(menuItem);
    }

}

