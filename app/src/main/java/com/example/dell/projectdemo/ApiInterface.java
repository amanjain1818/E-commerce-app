package com.example.dell.projectdemo;


import com.example.dell.projectdemo.pojo.AddCart;
import com.example.dell.projectdemo.pojo.Newarrival;
import com.example.dell.projectdemo.pojo.User;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("conform.jsp")
    Call <AddCart>conform(@Field("product_id") int product_id,
                            @Field("price") int price,
                            @Field("quantity") int quantity,
                            @Field("user_email") String user_email);

    @FormUrlEncoded
    @POST("conformord.jsp")
    Call <AddCart>conformord(@Field("product_id") int product_id,
                             @Field("totalprice") int totalprice,
                             @Field("quantity") int quantity,
                          @Field("user_email") String user_email,
                             @Field("address") String address);



    @FormUrlEncoded
    @POST("createaccount.jsp")
    Call <User>Createaccount(@Field("name") String name,
                            @Field("email") String email,
                            @Field("password") String password,
                            @Field("mobile") String mobile);
    @FormUrlEncoded
    @POST("login.jsp")
    Call <User>Login(@Field("email") String email,
                     @Field("password") String password);


    @FormUrlEncoded
    @POST("addcart.jsp")
    Call<AddCart> addToCart(@Field("product_id") int product_id,
                                  @Field("price") int price,
                                  @Field("quantity") int quantity,
                                  @Field("user_email") String user_email);


    @GET("saree_data.jsp")
    Call<List<Newarrival>> sareeFragment();

    @GET("lehnga_data.jsp")
    Call<List<Newarrival>> lehngaFragment();

    @GET("newarrival.jsp")
    Call<List<Newarrival>> newarrivalFragment();

    @GET("cartlis.jsp")
    Call<List<AddCart>> cartlist(@Query("user_email")String email);



    @GET("remove.jsp")
    Call<AddCart> removelist(@Query("user_email")String email,
                                   @Query("id")int id);

}
