package com.example.my_divar3;

import androidx.appcompat.app.ActionBar;
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
import com.example.my_divar3.models.helper.DeliverymanType;
import com.example.my_divar3.models.helper.Status;
import com.example.my_divar3.models.product.Product;
import com.example.my_divar3.models.user.Admin;
import com.example.my_divar3.models.user.Buyer;
import com.example.my_divar3.models.user.Deliveryman;
import com.example.my_divar3.models.user.Seller;
import com.example.my_divar3.models.user.User;

import java.util.ArrayList;
import java.util.List;

public class AdminPageActivity extends AppCompatActivity {
    Spinner spinner;
    RecyclerView dataRecyclerView;
    EditText editText;
    int spinnerValue=0;
    Button submitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Admin : "+ Database.currentUser.getUsername());
        }
        spinner = findViewById(R.id.actionSpinner);
        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        editText = findViewById(R.id.editTextTextPersonName3);
        submitBtn = findViewById(R.id.button5);


        List<String> items = new ArrayList<>();
        items.add("1-show Users and delete");
        items.add("2-show Ads and delete");
        items.add("3-show Request and accept");
        items.add("4-show and assign Delivery");
        if(Database.currentUser.getUsername().equals("admin")) {
            items.add("5-Show users and pay salary");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        dataRecyclerView = findViewById(R.id.dataRecyclerView);
        dataRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final RecyclerView.Adapter[] recyclrAdapterAdapter = {new UserRecyclrAdapter(Database.getAllUsers())};


        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                spinnerValue=position;
                switch (spinnerValue) {
                    case 0:
                        editText.setHint("userName you want to delete");

                        dataRecyclerView.setAdapter(new UserRecyclrAdapter(Database.getAllUsers()));
                        break;
                    case 1:
                        editText.setHint("product name you want to delete");
                        dataRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getAllAds()));
                        break;
                    case 2:
                        editText.setHint("product name you want to accept");
                        dataRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getRequests()));
                        break;
                    case 3:
                        editText.setHint("product name you want to deliver");
                        dataRecyclerView.setAdapter(new ProductRecyclrAdapter(Database.getWaitForShipping()));
                        break;
                    case 4:
                        editText.setHint("userName pay salary,amout");
                        dataRecyclerView.setAdapter(new UserRecyclrAdapter(Database.getAllUsers()));
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
                switch (spinnerValue) {
                    case 0:
                        found =0;
                        for (int i = 0; i <Database.getAllUsers().size(); i++) {
                            if(Database.getAllUsers().get(i).getUsername().equals(editText.getText().toString())){
                                found =1;
                                Database.getAllUsers().remove(i);
                                Toast.makeText(AdminPageActivity.this, "User Delted", Toast.LENGTH_SHORT).show();
                            }

                        }
                        if (found == 0) {
                            Toast.makeText(AdminPageActivity.this, "Use not found", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        found =0;
                        for (int i = 0; i <Database.getAllAds().size(); i++) {
                            if(Database.getAllAds().get(i).getProductName().equals(editText.getText().toString())){
                                found =1;
                                Database.getAllAds().remove(i);
                                Toast.makeText(AdminPageActivity.this, "Product Delted", Toast.LENGTH_SHORT).show();
                            }

                        }
                        if (found == 0) {
                            Toast.makeText(AdminPageActivity.this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        found =0;
                        for (int i = 0; i <Database.getRequests().size(); i++) {
                            if(Database.getRequests().get(i).getProductName().equals(editText.getText().toString())){
                                found =1;
                                Database.getRequests().get(i).setStatus(Status.READY);
                                Database.getAllAds().add(Database.getRequests().get(i));
                                Database.getRequests().remove(i);
                                Toast.makeText(AdminPageActivity.this, "request accepted", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (found == 0) {
                            Toast.makeText(AdminPageActivity.this, "Product not found", Toast.LENGTH_SHORT).show();
                        }
                    case 3:
                        Product p = null;
                        for (int i = 0; i < Database.getWaitForShipping().size(); i++) {
                            System.out.println(Database.getWaitForShipping().get(i).toString());
                        }

                        String prodcutName =editText.getText().toString();
                        for (int i = 0; i < Database.getWaitForShipping().size(); i++) {
                            if (Database.getWaitForShipping().get(i).getProductName().equals(prodcutName)) {
                                p = Database.getWaitForShipping().get(i);
                            }
                        }
                        if (p == null) {
                            Toast.makeText(AdminPageActivity.this, "--- EROR : Dont Have This prdocut ! --- ", Toast.LENGTH_SHORT).show();

                        }else{
                            ArrayList<Deliveryman> available = new ArrayList<Deliveryman>();
                            for (int i = 0; i < Database.getDeliveryMen().size(); i++) {
                                if (Database.getDeliveryMen().get(i).getBusyTime() > 0) {
                                    System.out.println(i + "Busy " + Database.getDeliveryMen().get(i));
                                } else if (p.getCategory() == Category.HOME
                                        && Database.getDeliveryMen().get(i).getDeliverymanType() != DeliverymanType.VANET) {
                                    System.out.println(i + "Not sutiable " + Database.getDeliveryMen().get(i));
                                } else {
                                    System.out.println(i + "Available " + Database.getDeliveryMen().get(i));
                                    available.add(Database.getDeliveryMen().get(i));
                                }
                            }
                            if (available.size() == 0) {
                                Toast.makeText(AdminPageActivity.this, "No DeliveryMan is available", Toast.LENGTH_SHORT).show();
                            }else{
                                Seller seller = null;
                                for (Seller s : Database.getSellers()) {
                                    if (s.getUsername().equals(p.getSellerUsername())) {
                                        seller = s;
                                    }
                                }
                                int minDistance = Integer.MAX_VALUE;
                                int indexOfMinDistance = -1;
                                for (int i = 0; i < available.size(); i++) {
                                    int distance = seller.getLocation().getDistance(available.get(i).getLocation());
                                    if (distance <= minDistance) {
                                        minDistance = distance;
                                        indexOfMinDistance = i;
                                    }
                                }
                                p.setStatus(Status.SHIPPING);
                                available.get(indexOfMinDistance).setProduct(p);
                                available.get(indexOfMinDistance).setBusyTime(minDistance * 3);
                                Toast.makeText(AdminPageActivity.this, "Your product is getting shipped by "+ available.get(indexOfMinDistance).toString(), Toast.LENGTH_SHORT).show();

                            }
                        }
                        break;
                    case 4:
                        found =0;
                        for (User u : Database.getAllUsers()) {
                            if (u.getUsername().equals(editText.getText().toString().split(",")[0])) {
                                found = 1;
                                int salary = Integer.parseInt(editText.getText().toString().split(",")[1]);
                                u.addToWallet(salary);
                                Toast.makeText(AdminPageActivity.this, "Salary paid", Toast.LENGTH_SHORT).show();
                            }
                        }
                        if (found == 0) {
                            Toast.makeText(AdminPageActivity.this, "Use not found", Toast.LENGTH_SHORT).show();
                        }

                        break;
                    default:
                        break;
                }
            }
        });
        
        recyclrAdapterAdapter[0].notifyDataSetChanged();
    }
}