package com.sayeedul.dblogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sayeedul.dblogin.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {


    DatabaseHelper helper = new DatabaseHelper(this);
    EditText user,pass;
    Button login,signup,reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)findViewById(R.id.emailET);
        pass = (EditText)findViewById(R.id.passwordET);
        login = (Button)findViewById(R.id.loginBTN);
        signup = (Button)findViewById(R.id.signupBTN);
        reset = (Button)findViewById(R.id.resetBTN);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String USER = user.getText().toString();
                String PASS = pass.getText().toString();

                String PASSWORD = helper.searchPass(USER);
                if(PASS.equals(PASSWORD))
                {
                    String TYPE = helper.searchType(USER);
                    if(TYPE.equalsIgnoreCase("Student"))
                    {
                        // STUDENT ACTIVITY....
                        Toast.makeText(MainActivity.this, "STUDENT ACTIVTY INITIALIZING...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,StudentActivity.class);
                        intent.putExtra("USERNAME",USER);
                        startActivity(intent);
                    }
                    else
                    {
                        // ADMIN ACTIVITY
                        Toast.makeText(MainActivity.this, "ADMIN ACTIVTY INITIALIZING...", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this,AdminActivity.class);
                        intent.putExtra("USERNAME",USER);
                        startActivity(intent);
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, " WRONG USERNAME or PASSWORD ! ", Toast.LENGTH_LONG).show();
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setText("");
                pass.setText("");
            }
        });
    }
}
