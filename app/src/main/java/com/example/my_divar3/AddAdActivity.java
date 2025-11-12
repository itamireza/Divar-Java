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
import android.widget.Toast;

import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.helper.Category;
import com.example.my_divar3.models.helper.Status;
import com.example.my_divar3.models.product.Product;

import java.util.ArrayList;
import java.util.List;

public class AddAdActivity extends AppCompatActivity {
    EditText productName;
    EditText productPrice;
    EditText productSellerName;
    Spinner spinner;
    int spinnerValue =0;

    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ad);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Create a new add");
        }

        spinner = findViewById(R.id.spinnerCategory);
        productName = findViewById(R.id.editTextProductName);
        productPrice = findViewById(R.id.editTextProductPrice);
        productSellerName = findViewById(R.id.editTextSellerName);
        submitBtn = findViewById(R.id.button10);


        List<String> items = new ArrayList<>();
        items.add("1-PHONE");
        items.add("2-HOME");
        items.add("3-STATIONARY");
        items.add("4-CLOTHOING");
        items.add("5-CAR");
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


        Category category = Category.PHONE;
        switch (spinnerValue) {
            case 0:
                category = Category.PHONE;
                break;
            case 1:
                category = Category.HOME;
                break;
            case 2:
                category = Category.STATIONARY;
                break;
            case 3:
                category = Category.CLOTHING;
                break;
            case 4:
                category = Category.CAR;
                break;

            default:
                break;
        }
        Product product = new Product(productName.getText().toString(), Integer.parseInt(productPrice.getText().toString()), productSellerName.getText().toString(), category, Status.REQUEST);
        Database.getRequests().add(product);

        Toast.makeText(AddAdActivity.this, "Your Ad is in the request list", Toast.LENGTH_SHORT).show();
        finish();
            }
        });

    }

}