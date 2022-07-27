package com.androdev.azadproject.Connection;

import com.androdev.azadproject.Model.LoginModel;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ConnectionInterface {

    @POST("login.php")
    Call<ResponseBody> login(@Body LoginModel params);
}
