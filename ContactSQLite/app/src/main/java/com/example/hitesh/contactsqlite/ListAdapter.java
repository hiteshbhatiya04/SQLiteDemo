package com.example.hitesh.contactsqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;



public class ListAdapter extends BaseAdapter {
    Context context;
    ListActivity listActivity;
    ArrayList<HashMap<String,String>> hashMapArrayList;
    int list_layout;
    String[] strings;
    LayoutInflater inflater;



    public ListAdapter(ListActivity listActivity, ArrayList<HashMap<String, String>> hashMapArrayList, int list_layout)
    {
        this.listActivity =listActivity;
        this.hashMapArrayList = hashMapArrayList;
        this.list_layout =list_layout;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return hashMapArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = inflater.from(listActivity).inflate(list_layout,null);
        TextView name = (TextView)view.findViewById(R.id.c_name);
        TextView phone = (TextView)view.findViewById(R.id.c_phone);
        TextView email = (TextView)view.findViewById(R.id.c_email);
        name.setText(hashMapArrayList.get(i).get("name"));
        phone.setText(hashMapArrayList.get(i).get("phone"));
        email.setText(hashMapArrayList.get(i).get("email"));




        return view;
    }
}
