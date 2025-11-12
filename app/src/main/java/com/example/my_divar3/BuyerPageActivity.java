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
import com.example.my_divar3.models.user.Buyer;
import com.example.my_divar3.models.user.Seller;

public class BuyerPageActivity extends AppCompatActivity {
    Button profileBtn;
    Button historyBtn;
    Button salesAdBtn;
    RecyclerView buyerSavedBoxRecyclerView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_page);



        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Buyer : " + Database.currentUser.getUsername());
        }

        profileBtn = findViewById(R.id.button6);
        historyBtn = findViewById(R.id.button7);
        salesAdBtn = findViewById(R.id.button8);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerPageActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerPageActivity.this,HistoryActivity.class);
                startActivity(intent);
            }
        });

        salesAdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyerPageActivity.this,SalesAdActivity.class);
                startActivity(intent);
            }
        });
        buyerSavedBoxRecyclerView = findViewById(R.id.buyerSavedBoxRecyclerView);

        buyerSavedBoxRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Buyer buyer = (Buyer) Database.currentUser;
        ProductRecyclrAdapter adapter = new ProductRecyclrAdapter(buyer.getSavedBox());
        buyerSavedBoxRecyclerView.setAdapter(adapter);
    }
}