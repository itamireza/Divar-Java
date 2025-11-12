package com.example.my_divar3.models.user;

import java.util.ArrayList;
import java.util.Scanner;



import com.example.my_divar3.db.Database;
import com.example.my_divar3.models.helper.DeliverymanType;
import com.example.my_divar3.models.helper.Location;
import com.example.my_divar3.models.product.Product;

public abstract class User {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private int wallet = 0;
    private Location location;
    private ArrayList<Product> history;

    public static Scanner scanner = new Scanner(System.in);

    public User(String username, String password, String email, String phoneNumber, Location location) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.history = new ArrayList<Product>();
    }

    public static boolean checkEmail(String email2) {
        boolean temp = false;
        if (email2.matches("^(.+)@(gmail|yahoo)\\.com$")) {
            temp = true;
        }
        return temp;

    }

    public static boolean checkPhonenumber(String phonenumber2) {
        boolean temp = false;
        if (phonenumber2.matches("^09\\d{9}$")) {
            temp = true;
        }
        return temp;

    }

    public static boolean checkPassword(String password2) {
        boolean temp = false;
        if (password2.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%&]).{6,20}$")) {
            temp = true;
        }
        return temp;

    }

    // --------------- Getter & Setters -----------
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Product> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Product> history) {
        this.history = history;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void addToWallet(int amount) {
        this.wallet += amount;
    }

    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", email=" + email + ", phoneNumber="
                + phoneNumber + ", wallet=" + wallet + "]";
    }

    @Override
    public boolean equals(Object obj) {
        User other = (User) obj;
        return (this.username == other.username && this.email == other.email && this.email == other.email
                && this.phoneNumber == other.phoneNumber);
    }

}
