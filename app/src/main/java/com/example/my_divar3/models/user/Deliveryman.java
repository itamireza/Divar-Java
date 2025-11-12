package com.example.my_divar3.models.user;


import com.example.my_divar3.models.helper.DeliverymanType;
import com.example.my_divar3.models.helper.Location;
import com.example.my_divar3.models.product.Product;

public class Deliveryman extends User {

    private Product product;
    private int busyTime;
    private DeliverymanType deliverymanType;

    public Deliveryman(String username, String password, String email, String phoneNumber, Location location,
            DeliverymanType deliverymanType) {
        super(username, password, email, phoneNumber, location);
        this.busyTime = 0;
        this.deliverymanType = deliverymanType;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBusyTime() {
        return busyTime;
    }

    public void setBusyTime(int busyTime) {
        this.busyTime = busyTime;
    }

    public DeliverymanType getDeliverymanType() {
        return deliverymanType;
    }

}
