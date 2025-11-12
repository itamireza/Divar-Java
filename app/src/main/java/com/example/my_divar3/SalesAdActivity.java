package com.example.my_divar3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.my_divar3.RecyclerAdapters.ProductRecyclrAdapter;
import com.example.my_divar3.RecyclerAdapters.UserRecyclrAdapter;
import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.helper.Category;
import com.example.my_divar3.models.helper.Status;
import com.example.my_divar3.models.product.Product;
import com.example.my_divar3.models.user.Seller;

import java.util.ArrayList;
import java.util.List;

public class SalesAdActivity extends AppCompatActivity {
    Spinner spinner;
    Button submitBtn;
    EditText editText;
    RecyclerView productRecyclerView;
    int spinnerValue =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_ad);




        spinner = findViewById(R.id.chooseCategorySpinner);
        editText =findViewById(R.id.editTextTextPersonName3);
        submitBtn = findViewById(R.id.button5);
        productRecyclerView=findViewById(R.id.productRecyclerView);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> items = new ArrayList<>();
        items.add("1-ALL");
        items.add("2-PHONE");
        items.add("3-HOME");
        items.add("4-STATIONARY");
        items.add("5-CLOTHOING");
        items.add("6-CAR");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerValue=position;
                Category category = Category.PHONE;
                switch (spinnerValue) {
                    case 0:
                        productRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getAllAds()));
                        break;
                    case 1:
                        category = Category.PHONE;
                        productRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getAdsByCatergory(category)));
                        break;
                    case 2:
                        category = Category.HOME;
                        productRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getAdsByCatergory(category)));
                        break;
                    case 3:
                        category = Category.STATIONARY;
                        productRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getAdsByCatergory(category)));
                        break;
                    case 4:
                        category = Category.CLOTHING;
                        productRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getAdsByCatergory(category)));
                        break;
                    case 5:
                        category = Category.CAR;
                        productRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getAdsByCatergory(category)));
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
                int found =0;
                for (int i = 0; i <Database.getAllAds().size(); i++) {
                    if(Database.getAllAds().get(i).getProductName().equals(editText.getText().toString())){
                        found =1;
                        Product selectedProduct = Database.getAllAds().get(i);
                        for (int j = 0; j < Database.getSellers().size(); j++) {
                            if (Database.getSellers().get(j).getUsername()
                                    .equals(selectedProduct.getSellerUsername())) {
                                Seller seller = Database.getSellers().get(j);
                                seller.getHistory().add(selectedProduct);
                                Database.currentUser.getHistory().add(selectedProduct);
                                int p = selectedProduct.getProductPrice();
                                seller.addToWallet((int) (0.9 * p));
                                Database.currentUser.setWallet(Database.currentUser.getWallet()-p);
                                Database.mainAdmin.addToWallet((int) (0.1 * p));
                                selectedProduct.setStatus(Status.WAITFORSHIPPING);
                                Database.getAllAds().remove(i);
                            }
                        }
                        Toast.makeText(SalesAdActivity.this, "Product purchased successfully", Toast.LENGTH_SHORT).show();
                    }

                }
                if (found == 0) {
                    Toast.makeText(SalesAdActivity.this, "Use not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}