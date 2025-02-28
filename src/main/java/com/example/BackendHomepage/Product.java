package com.example.BackendHomepage;

public class Product {
    private int id;
    private String image;
    private String title;
    private double price;
    private String description;
    private int rating;

    public Product() {
    }
    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getRating() {
        return rating;
    }
}
