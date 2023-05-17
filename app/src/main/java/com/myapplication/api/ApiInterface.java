package com.myapplication.api;


import com.google.gson.JsonElement;
import com.myapplication.Adapter.UserdataAdapter;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiInterface {

    @GET
    Call<JsonElement> getWebserviceCall(@Url String url);

    @GET
    Call<JsonElement> getWebserviceCall(@Url String url, @Header("Authorization") String authorization);

    @GET
    Call<JsonElement> getWebserviceCall(@Url String url, @QueryMap HashMap<String, Object> body);

    @POST
    Call<JsonElement> postWebserviceCall(@Url String url, @Body HashMap<String, Object> body);


   /* //Get Api Demo
    @GET("category/getCategory")
    Call<CommonResponse<List<GetCategorydata>>> getCategory();

    //PostApiDemo
    @POST("user/registration")
    Call<SignUpResponse> registration(@Body RegistrationRequest registrationRequest);


    //MultiPart Demo
    /*@Multipart
    @POST("schedule/saveBroadcastAndScheduledDetails")
    Call<AddBroadcastResponse<AddBroadcastScheduleResponse>> saveBroadcastAndScheduledDetails(@Part List<MultipartBody.Part> build, @Part MultipartBody.Part profileImage);*/


    @GET("users")
    Call<com.apicalldemo.model.UserListData> userlist();


}