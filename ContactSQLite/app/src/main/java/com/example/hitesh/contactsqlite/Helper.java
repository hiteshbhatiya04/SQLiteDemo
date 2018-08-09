package com.example.hitesh.contactsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Helper extends SQLiteOpenHelper {


    public static int DB_version = 1;
    public static String DB_name = "DB_CONTACT";
    public static String TB_name = "Contact";
    public static String user_id = "id";
    public static String user_name = "name";
    public static String user_phone = "phone";
    public static String user_email = "email";

    public Helper(Context context) {
        super(context,DB_name,null,DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String query="create table "+TB_name+"("+user_id+" integer primary key, "+user_name+" text, "+user_phone+" text, "+user_email+" text)";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists "+TB_name);
        onCreate(sqLiteDatabase);

    }

    public void AddContact(Contact contact){
        SQLiteDatabase liteDatabase =this.getWritableDatabase();
        ContentValues values =new ContentValues();
        values.put(user_name,contact.getName());
        values.put(user_phone,contact.getPhone());
        values.put(user_email,contact.getEmail());
        liteDatabase.insert(TB_name,null,values);
        liteDatabase.close();

    }
    List<Contact> ReadAllContact(){

        java.util.List<Contact>contactList =new ArrayList<>();
        SQLiteDatabase database =this.getReadableDatabase();
        String rc ="select * from "+TB_name;
        Cursor cursor = database.rawQuery(rc,null);
        if (cursor.moveToNext())
        {
            do {
                Contact contact =new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhone(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contactList.add(contact);

            }while (cursor.moveToNext());
        }return (List<Contact>) contactList;

    }

    public void delete(Contact contact) {
        SQLiteDatabase database=this.getWritableDatabase();
        database.delete(TB_name,user_id+" =?",new String[]{String.valueOf(contact.getId())});
        database.close();
    }

    public int Update(Contact contact) {

        SQLiteDatabase database=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(user_name,contact.getName());
        values.put(user_phone,contact.getPhone());
        values.put(user_email,contact.getEmail());

        int i = database.update(TB_name,values,user_id+" =?",new String[]{String.valueOf(contact.getId())});
        return i;

    }
}

