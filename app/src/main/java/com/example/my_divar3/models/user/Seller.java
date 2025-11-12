package com.example.my_divar3.models.user;

import java.util.ArrayList;


import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.helper.*;
import com.example.my_divar3.models.product.Product;

public class Seller extends User {

    private ArrayList<Product> ads;

    public Seller(String username, String password, String email, String phoneNumber, Location location) {
        super(username, password, email, phoneNumber, location);
        this.ads = new ArrayList<>();
    }

    public ArrayList<Product> getAds() {
        return ads;
    }

}
