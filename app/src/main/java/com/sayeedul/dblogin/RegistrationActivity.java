package com.sayeedul.dblogin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sayeedul.dblogin.database.DatabaseHelper;
import com.sayeedul.dblogin.model.ContactData;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatabaseHelper helper;
    SQLiteDatabase db;

    Button sign;
    EditText user,pass,gend;

    String username,password,gender,cntry,typ;
    Integer pos1,pos2;

    String [] country = {"India","China","Pakistan","USA","BHUTAN","UAE"};
    String [] type = {"Student","Admin"};

    Spinner CountrySpinner,TypeSpinner;
    ArrayAdapter<String> countryAdapter,typeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ragistration_main);

        user = (EditText)findViewById(R.id.userET);
        pass = (EditText)findViewById(R.id.passET);
        sign = (Button)findViewById(R.id.signupBTN);
        gend = (EditText)findViewById(R.id.genderET);

        username = user.getText().toString();
        password = pass.getText().toString();
        gender = gend.getText().toString();

        CountrySpinner = (Spinner)findViewById(R.id.countrySPINNER);
        TypeSpinner = (Spinner)findViewById(R.id.typeSPINNER);

        countryAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,country);
        CountrySpinner.setAdapter(countryAdapter);

        typeAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,type);
        TypeSpinner.setAdapter(typeAdapter);

        CountrySpinner.setOnItemSelectedListener(RegistrationActivity.this);
        TypeSpinner.setOnItemSelectedListener(RegistrationActivity.this);


        helper = new DatabaseHelper(this);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegistrationActivity.this, MainActivity.class);

                ContactData cd =new ContactData();
                cd.setUser(username);
                cd.setPass(password);
                cd.setCountry(cntry);
                cd.setGender(gender);
                cd.setType(typ);

                helper.insertdata(cd);
                Toast.makeText(getApplicationContext(), "Registered successfully",Toast.LENGTH_LONG).show();

                startActivity(i);
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(view.getId())
        {
            case R.id.countrySPINNER :  cntry = country[position];
                                         pos1 = position;
                                        break;

            case R.id.typeSPINNER :    typ = type[position];
                                         pos2 = position;
                                        break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

        switch(parent.getId())
        {
            case R.id.countrySPINNER :
                Toast.makeText(RegistrationActivity.this, "NOTHING SELECTED, SELECT COUNTRY.", Toast.LENGTH_SHORT).show();
                break;

            case R.id.typeSPINNER :
                Toast.makeText(RegistrationActivity.this, "NOTHING SELECTED, SELECT TYPE.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
