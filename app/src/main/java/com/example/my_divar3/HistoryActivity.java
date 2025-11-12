package com.example.my_divar3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.my_divar3.RecyclerAdapters.ProductRecyclrAdapter;
import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.user.Seller;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView userHistoryRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("History : " + Database.currentUser.getUsername());
        }

        userHistoryRecycler = findViewById(R.id.userHistoryRecycler);

        userHistoryRecycler.setLayoutManager(new LinearLayoutManager(this));
        ProductRecyclrAdapter adapter = new ProductRecyclrAdapter(Database.currentUser.getHistory());
        userHistoryRecycler.setAdapter(adapter);

    }
}