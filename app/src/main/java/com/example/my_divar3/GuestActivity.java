package com.example.my_divar3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.my_divar3.RecyclerAdapters.ProductRecyclrAdapter;
import com.example.my_divar3.db.Database;

public class GuestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Guest Menu");
        }
        recyclerView = findViewById(R.id.adRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ProductRecyclrAdapter adapter = new ProductRecyclrAdapter(Database.getAllAds());
        recyclerView.setAdapter(adapter);
    }
}