package com.example.my_divar3.RecyclerAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_divar3.R;
import com.example.my_divar3.models.product.Product;

import java.util.ArrayList;

public class ProductRecyclrAdapter extends RecyclerView.Adapter<ProductRecyclrAdapter.ViewHolder> {
    private ArrayList<Product> itemList;

    public ProductRecyclrAdapter(ArrayList<Product> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_product_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product currentItem = itemList.get(position);
        String output = "Product Name : " +currentItem.getProductName() + " Product Price : "+currentItem.getProductPrice();
        holder.itemTextView.setText(output);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTextView = itemView.findViewById(R.id.textViewProductRecyclerLayout);
        }
    }
}
