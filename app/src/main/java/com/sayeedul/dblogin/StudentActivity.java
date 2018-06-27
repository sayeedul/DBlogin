package com.sayeedul.dblogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sayeedul.dblogin.database.DatabaseHelper;

public class StudentActivity extends AppCompatActivity {

    Button show,edit;
    TextView detail;
    String user,getd;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        show = (Button)findViewById(R.id.showBTN);
        edit = (Button)findViewById(R.id.editBTN);
        detail = (TextView)findViewById(R.id.detailET);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show details.
                user = getIntent().getStringExtra("USERNAME");
                getd = helper.readData(user);

                detail.setText("HELLO "+ user +" \n "+ getd );
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentActivity.this,EditAcitivity.class);
                startActivity(i);
            }
        });
    }
}
