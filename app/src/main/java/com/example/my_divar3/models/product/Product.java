package com.example.my_divar3.models.product;

import com.example.my_divar3.models.helper.Category;
import com.example.my_divar3.models.helper.Status;

import java.util.ArrayList;


public class Product {

    private String productName;
    private int productPrice;
    private String sellerUsername;
    private Category category;
    private Status status;
    private ArrayList<String> comments;

    public Product(String productName, int string, String sellerUsername, Category category, Status status) {
        this.productName = productName;
        this.productPrice = string;
        this.sellerUsername = sellerUsername;
        this.category = category;
        this.status = status;
        this.comments = new ArrayList<String>();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public void setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Product [productName=" + productName + ", productPrice=" + productPrice + ", sellerUsername="
                + sellerUsername + ", category=" + category + ", status=" + status + ", comments=" + comments.toString()
                + "]";
    }

}
