package com.example.my_divar3.RecyclerAdapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.my_divar3.R;
import com.example.my_divar3.models.user.User;

import java.util.ArrayList;

public class UserRecyclrAdapter extends RecyclerView.Adapter<UserRecyclrAdapter.ViewHolder> {
    private ArrayList<User> itemList;
    public UserRecyclrAdapter(ArrayList<User> itemList){
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_user_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User currentItem = itemList.get(position);
        String output = "Username : "+currentItem.getUsername()+" Email : "+currentItem.getEmail();
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
            itemTextView = itemView.findViewById(R.id.UserRecyclerLayout);
        }
    }
}
