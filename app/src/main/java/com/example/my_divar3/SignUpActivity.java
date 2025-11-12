package com.example.my_divar3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.helper.DeliverymanType;
import com.example.my_divar3.models.helper.Location;
import com.example.my_divar3.models.user.*;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    Spinner spinner;
    Button submitBtn;
    EditText emailField;
    EditText passField;
    EditText usernameField;
    EditText phone;
    EditText locationField;
    int spinnerValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Sign Up");
        }
        spinner = findViewById(R.id.answerSpinner) ;
        submitBtn = findViewById(R.id.button1);
        emailField = findViewById(R.id.editTextTextEmailAddress) ;
        passField = findViewById(R.id.editTextTextPassword);
        usernameField = findViewById(R.id.editTextTextPersonName) ;
        phone = findViewById(R.id.editTextTextPersonName);
        locationField = findViewById(R.id.editTextTextLocation);


        List<String> items = new ArrayList<>();
        items.add("1-Admin");
        items.add("2-Seller");
        items.add("3-Buyer");
        items.add("4-DeliveryMan (Motor)");
        items.add("5-DeliveryMan (Vanet)");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerValue=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });




    }
    public void signup(){
        String phonenumber = phone.getText().toString();
        String email = emailField.getText().toString();
        String password = passField.getText().toString();
        String username = usernameField.getText().toString();
        String locationText=locationField.getText().toString();
        Double lat = Double.parseDouble(locationText.split(",")[0]);
        Double lon = Double.parseDouble(locationText.split(",")[1]);
        Location location = new Location(lat,lon);
        if (User.checkEmail(email) == true && User.checkPassword(password) == true ) {

            for (int i = 0; i < Database.getAllUsers().size(); i++) {
                if (Database.getAllUsers().get(i).getUsername().equals(username)) {
                    Toast.makeText(SignUpActivity.this, "EROR : This User Is Already Used", Toast.LENGTH_SHORT).show();
                } else {
                    User newUser = null;
                    DeliverymanType type = null;
                    switch (spinnerValue) {
                        case 0:
                            newUser = new Admin(username, password, email, phonenumber);
                            break;
                        case 1:
                            newUser = new Seller(username, password, email, phonenumber, location);
                            break;
                        case 2:
                            newUser = new Buyer(username, password, email, phonenumber, location);
                            break;
                        case 3:
                            type = DeliverymanType.MOTOR;
                            newUser = new Deliveryman(username, password, email, phonenumber, location, type);
                            break;
                        case 4:
                            type = DeliverymanType.VANET;
                            newUser = new Deliveryman(username, password, email, phonenumber, location, type);
                            break;
                        default:
                            break;
                    }
                    Toast.makeText(SignUpActivity.this, " --- Your Signup Is Successfull ! ---", Toast.LENGTH_SHORT).show();
                    Database.getAllUsers().add(newUser);
                    finish();
                }

            }


        } else {
            Toast.makeText(SignUpActivity.this, "You Did Not Enter Your Information Correctly", Toast.LENGTH_SHORT).show();
        }

    }
}