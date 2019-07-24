package com.example.dell.projectdemo.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Newarrival implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("image_path")
    String image;
    @SerializedName("name")
    String name;
    @SerializedName("price")
    String price;

    @SerializedName("quantity")
    String quantity ;

    @SerializedName("categories_id")
    String categories_id;

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getCategories_id() {
        return categories_id;
    }

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}
