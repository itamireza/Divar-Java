package com.example.my_divar3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.my_divar3.RecyclerAdapters.ProductRecyclrAdapter;
import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.user.Seller;

public class SellerPageActivity extends AppCompatActivity {
    Button profileBtn;
    Button historyBtn;
    Button addAdBtn;
    RecyclerView sellerAdsRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_page);


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Seller : " + Database.currentUser.getUsername());
        }

        profileBtn = findViewById(R.id.button6);
        historyBtn = findViewById(R.id.button7);
        addAdBtn = findViewById(R.id.button9);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerPageActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerPageActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        addAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerPageActivity.this,AddAdActivity.class);
                startActivity(intent);
            }
        });
        sellerAdsRecyclerView = findViewById(R.id.sellerAdsRecyclerView);

        sellerAdsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Seller seller = (Seller) Database.currentUser;
        ProductRecyclrAdapter adapter = new ProductRecyclrAdapter(seller.getAds());
        sellerAdsRecyclerView.setAdapter(adapter);
    }
}