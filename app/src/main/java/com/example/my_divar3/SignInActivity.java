package com.example.my_divar3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.user.Admin;
import com.example.my_divar3.models.user.Buyer;
import com.example.my_divar3.models.user.Deliveryman;
import com.example.my_divar3.models.user.Seller;
import com.example.my_divar3.models.user.User;

public class SignInActivity extends AppCompatActivity {
    EditText usernameField;
    EditText passField;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Sign in");
        }
        usernameField = findViewById(R.id.editTextTextUsername);
        passField = findViewById(R.id.editTextTextPassword);
        submitBtn = findViewById(R.id.button1);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User u = signin();
                if(u!=null){
                    Database.currentUser =u;
                    Class destination = AdminPageActivity.class;
                    if(u instanceof Admin){
                        destination  = AdminPageActivity.class;
                    }else if(u instanceof Seller){
                        destination  = SellerPageActivity.class;
                    }else if(u instanceof Deliveryman){
                        destination  = SellerPageActivity.class;
                    }else if(u instanceof Buyer){
                        destination  = BuyerPageActivity.class;
                    }
                    Intent intent = new Intent(SignInActivity.this,destination);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignInActivity.this, "Username/password are incorrect. Please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public User signin(){
        String username = usernameField.getText().toString();
        String pass = passField.getText().toString();

        for (int i = 0; i < Database.getAllUsers().size(); i++) {
            if (username.equals(Database.getAllUsers().get(i).getUsername())
                    && pass.equals(Database.getAllUsers().get(i).getPassword())) {
                return Database.getAllUsers().get(i);
            }
        }
        return null;
    }
}