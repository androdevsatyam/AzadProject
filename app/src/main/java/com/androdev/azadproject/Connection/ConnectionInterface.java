package com.androdev.azadproject.Connection;

import com.androdev.azadproject.Model.LoginModel;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConnectionInterface {

    @POST("{location}")
    Call<ResponseBody> login(@Path("location")String location,@Body LoginModel params);
}
