package com.sayeedul.dblogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sayeedul.dblogin.database.DatabaseHelper;

public class DeleteActivity extends AppCompatActivity {

    EditText userDelete;
    Button dlt;
    DatabaseHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_main);

        userDelete = (EditText)findViewById(R.id.deleteUSER);
        dlt = (Button)findViewById(R.id.deleteBTN);

        dlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userDelete.getText().toString();
                helper.deleteData(user);
                Toast.makeText(DeleteActivity.this, "DELTED "+user+" SUCCESSFULLY...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(DeleteActivity.this,AdminActivity.class);
                startActivity(i);
            }
        });
    }
}
