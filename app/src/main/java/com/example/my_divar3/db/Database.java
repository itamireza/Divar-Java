package com.example.my_divar3.db;

import com.example.my_divar3.models.user.*;
import com.example.my_divar3.models.product.*;
import com.example.my_divar3.models.helper.*;

import java.util.ArrayList;


public class Database {
    public static User currentUser;
    public static ArrayList<User> allUsers = new ArrayList<User>();
    public static ArrayList<Product> allAds = new ArrayList<Product>();
    public static ArrayList<Product> request = new ArrayList<Product>();
    public static ArrayList<Product> waitForShipping = new ArrayList<Product>();
    public static User mainAdmin = new Admin("admin", "admin", "admin", "123");

    static {
        allUsers.add(mainAdmin);
        User seller1 = new Seller("ali", "ali", "ali@gmail.com", "123", new Location(65, 54));
        User buyer1 = new Buyer("mmd", "mmd", "mmd@gmail.com", "123", new Location(44, 54));
        User delivery1 = new Deliveryman("amir", "amir", "amir@gmail.om", "123", new Location(33, 33),
                DeliverymanType.MOTOR);
        User admin1 = new Admin("hassan", "hassan", "hassan@gmail.com", "123");
        allUsers.add(seller1);
        allUsers.add(buyer1);
        allUsers.add(delivery1);
        allUsers.add(admin1);
        Product ad1 = new Product("S20",1000,"ali",Category.PHONE,Status.READY);
        Product ad4 = new Product("S22",1200,"ali",Category.PHONE,Status.READY);
        Product ad3 = new Product("POCO X3",800,"ali",Category.PHONE,Status.READY);
        Product ad2 = new Product("Iphone 15",1500,"ali",Category.PHONE,Status.READY);
        allAds.add(ad1);
        allAds.add(ad2);
        allAds.add(ad3);
        allAds.add(ad4);
    }

    // ---------------- Users -------------------
    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static ArrayList<Buyer> getBuyers() {
        ArrayList<Buyer> result = new ArrayList<Buyer>();
        for (User u : allUsers) {
            if (u instanceof Buyer) {
                Buyer b = (Buyer) u;
                result.add(b);
            }
        }
        return result;
    }

    public static ArrayList<Seller> getSellers() {
        ArrayList<Seller> result = new ArrayList<Seller>();
        for (User u : allUsers) {
            if (u instanceof Seller) {
                Seller b = (Seller) u;
                result.add(b);
            }
        }
        return result;
    }

    public static ArrayList<Admin> getAdmins() {
        ArrayList<Admin> result = new ArrayList<Admin>();
        for (User u : allUsers) {
            if (u instanceof Admin) {
                Admin b = (Admin) u;
                result.add(b);
            }
        }
        return result;
    }

    public static ArrayList<Deliveryman> getDeliveryMen() {
        ArrayList<Deliveryman> result = new ArrayList<Deliveryman>();
        for (User u : allUsers) {
            if (u instanceof Deliveryman) {
                Deliveryman b = (Deliveryman) u;
                result.add(b);
            }
        }
        return result;
    }

    // ---------------- ADS ---------------------
    public static ArrayList<Product> getAllAds() {
        return allAds;
    }

    public static ArrayList<Product> getRequests() {
        return request;
    }

    public static ArrayList<Product> getWaitForShipping() {
        return waitForShipping;
    }
    public static ArrayList<Product> getAdsByCatergory(Category c){
        ArrayList<Product> result = new ArrayList<Product>();
        for (Product p:allAds){
            if(p.getCategory().equals(c)){
                result.add(p);
            }
        }
        return result;
    }
}
