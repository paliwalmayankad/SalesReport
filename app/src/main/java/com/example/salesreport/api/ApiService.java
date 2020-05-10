package com.example.salesreport.api;

import com.example.salesreport.Models.CommonModels;
import com.example.salesreport.Models.ForgotPasswordModel;
import com.example.salesreport.Models.LoginModels;
import com.example.salesreport.Models.ProductModel;
import com.example.salesreport.Models.RegisterModel;

import java.util.HashMap;
import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("login")
    Call<LoginModels> performlogin(@Query("username") String username,@Query("password") String password);
    @GET("forgot-password")
    Call<ForgotPasswordModel> performforgotpassword(@Query("email") String email);
    @FormUrlEncoded
    @POST("register")
    Call<RegisterModel> performSignup(@FieldMap HashMap<String, String> userDetails);
    @FormUrlEncoded
    @POST("change-password")
    Call<CommonModels> forgotpassword(@FieldMap HashMap<String, String> userDetails);
    @GET("products")
    Call<ProductModel> getProductList(@Query("user_id") String userid);
}
