package com.example.my_divar3.models.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.example.my_divar3.models.helper.Category;
import com.example.my_divar3.models.helper.Location;
import com.example.my_divar3.models.helper.Status;
import com.example.my_divar3.models.product.Product;

public class Buyer extends User {
    private ArrayList<Product> savedBox;

    public Buyer(String username, String password, String email, String phoneNumber, Location location) {
        super(username, password, email, phoneNumber, location);
        this.savedBox = new ArrayList<>();
    }

    public ArrayList<Product> getSavedBox() {
        return savedBox;
    }




}
