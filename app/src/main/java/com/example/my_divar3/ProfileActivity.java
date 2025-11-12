package com.example.my_divar3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.helper.DeliverymanType;
import com.example.my_divar3.models.user.Admin;
import com.example.my_divar3.models.user.Buyer;
import com.example.my_divar3.models.user.Deliveryman;
import com.example.my_divar3.models.user.Seller;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    TextView usernameTextView;
    TextView passwordTextView;
    TextView emailTextView;
    TextView phoneTextView;
    TextView walletTextView;
    Button submitBtn;
    Spinner spinner;
    EditText newValueEditText;
    int spinnerValue =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Seller" + Database.currentUser.getUsername());
        }
        usernameTextView = findViewById(R.id.textViewUsernameValue);
        passwordTextView = findViewById(R.id.textViewPasswordValue);
        emailTextView = findViewById(R.id.textViewEmailValue);
        phoneTextView = findViewById(R.id.textViewPhoneNumberValue);
        walletTextView = findViewById(R.id.textViewWalletValue);
        newValueEditText = findViewById(R.id.editTextTextPersonName4);
        submitBtn = findViewById(R.id.button11);
        spinner = findViewById(R.id.fieldSpinner);

        usernameTextView.setText(Database.currentUser.getUsername());
        passwordTextView.setText(Database.currentUser.getPassword());
        emailTextView.setText(Database.currentUser.getEmail());
        phoneTextView.setText(Database.currentUser.getPhoneNumber());
        walletTextView.setText(String.valueOf(Database.currentUser.getWallet()));



        List<String> items = new ArrayList<>();
        items.add("1-username");
        items.add("2-email");
        items.add("3-password");
        items.add("4-phone number");
        items.add("5-wallet");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerValue=position;
                switch (spinnerValue) {
                    case 0:
                        newValueEditText.setText(Database.currentUser.getUsername());
                        break;
                    case 1:
                        newValueEditText.setText(Database.currentUser.getEmail());
                        break;
                    case 2:
                        newValueEditText.setText(Database.currentUser.getPassword());
                        break;
                    case 3:
                        newValueEditText.setText(Database.currentUser.getPhoneNumber());
                        break;
                    case 4:
                        newValueEditText.setText(String.valueOf(Database.currentUser.getWallet()));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (spinnerValue) {
                    case 0:
                        Database.currentUser.setUsername(newValueEditText.getText().toString());
                        break;
                    case 1:
                        Database.currentUser.setEmail(newValueEditText.getText().toString());
                        break;
                    case 2:
                        Database.currentUser.setPassword(newValueEditText.getText().toString());
                        break;
                    case 3:
                        Database.currentUser.setPhoneNumber(newValueEditText.getText().toString());
                        break;
                    case 4:
                        Database.currentUser.setWallet(Integer.parseInt(newValueEditText.getText().toString()));
                        break;
                    default:
                        break;
                }
                recreate();
            }

        });


    }
}