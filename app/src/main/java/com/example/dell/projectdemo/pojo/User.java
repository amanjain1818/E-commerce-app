package com.example.dell.projectdemo.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("response")
    String Response;
    @SerializedName("email")
    String email;

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    @SerializedName("username")
    String username;
    public String getResponse()
    {
        return Response;
    }


}

