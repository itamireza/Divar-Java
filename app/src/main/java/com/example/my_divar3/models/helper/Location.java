package com.example.my_divar3.models.helper;

public class Location {

    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getDistance(Location other) {
        double distance = Math.sqrt(((this.latitude - other.latitude) * (this.latitude - other.latitude))
                + ((this.longitude - other.longitude) * (this.longitude - other.longitude)));
        return (int) Math.ceil(distance);
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }

}
