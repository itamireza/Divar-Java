package com.example.my_divar3.models.user;

import java.util.ArrayList;


import com.example.my_divar3.models.helper.Category;
import com.example.my_divar3.models.helper.DeliverymanType;
import com.example.my_divar3.models.helper.Status;
import com.example.my_divar3.models.product.Product;

public class Admin extends User {

    public Admin(String username, String password, String email, String phoneNumber) {
        super(username, password, email, phoneNumber, null);
    }


}
