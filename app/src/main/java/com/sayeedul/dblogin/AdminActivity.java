package com.sayeedul.dblogin;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.sayeedul.dblogin.database.DatabaseHelper;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewdisplay_main);

        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new DatabaseHelper(this);

        //populate an ArrayList<String> from the database and then view it
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.getListContents();
        if(data.getCount() == 0){
            Toast.makeText(this, " There are no STUDENTS Registered...! ",Toast.LENGTH_LONG).show();
        }else{
            while(data.moveToNext()){
                String values = data.getString(1).toString()+ " " + data.getString(2).toString()+ " " +
                        data.getString(3).toString()+ " " + data.getString(4).toString() ;
                theList.add(values);
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }

    }

    public void listClick()
    {
        Toast.makeText(this, "Going to DELETE PAGE...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(AdminActivity.this,DeleteActivity.class);
        startActivity(i);
    }

}

