package com.example.restoapp.models;

import com.google.gson.annotations.SerializedName;

public class Meal {
    @SerializedName("name")
    private String name;
    @SerializedName("price")
    private String price;
    @SerializedName("description")
    private String description;
    @SerializedName("id")
    private int id;
    @SerializedName("imageURL")
    private String imageURL;

    public Meal(String name, String price, String description, int id, String imageURL) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.id = id;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
