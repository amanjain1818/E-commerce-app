package com.example.dell.projectdemo.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AddCart implements Serializable {
    @SerializedName("response")
    String Response;
    @SerializedName("image_path")
    String image;
    @SerializedName("name")
    String name;
    @SerializedName("id")
    int id;
    @SerializedName("totalprice")
    int totalprice;
    @SerializedName("price")
    int price;
    @SerializedName("quantity")
    int quantity;
    @SerializedName("product_id")
    int product_id ;
    @SerializedName("user_email")
    String user_email;
    @SerializedName("address")
    String address;
    @SerializedName("order_id")
    String order_id;

    public String getResponse() {
        return Response;
    }

    public String getImage() {
        return image;
    }

    public String getName()
    {
        return name;
    }

    public int getTotalprice() {
        return totalprice;
    }


    public String getUser_email() {
        return user_email;
    }


    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public int getPrice()
    {
        return price;
    }

    public int getQuantity()

    {
        return quantity;
    }

    public int getProduct_id()

    {
        return product_id;
    }

    public String getOrder_id() {
        return order_id;
    }
}
