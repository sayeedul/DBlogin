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

public class EditAcitivity extends AppCompatActivity {

    EditText user,pass;
    Button submit;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_main);

        user = (EditText)findViewById(R.id.editUSER);
        pass = (EditText)findViewById(R.id.editPASS);

        submit = (Button)findViewById(R.id.submitBTN);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = user.getText().toString();
                String b = pass.getText().toString();
                String old = getIntent().getStringExtra("USERNAME");
                helper.updateData(a,b,old);
                Toast.makeText(EditAcitivity.this, "UPDATE of "+ a +" SUCCESSFULL...", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(EditAcitivity.this,StudentActivity.class);
                startActivity(i);
            }
        });
    }
}
