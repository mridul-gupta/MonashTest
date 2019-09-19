package com.monash.sample.network

import com.monash.sample.pojo.UserData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/monashFunction")
    fun getUserData(): Call<UserData>
}
