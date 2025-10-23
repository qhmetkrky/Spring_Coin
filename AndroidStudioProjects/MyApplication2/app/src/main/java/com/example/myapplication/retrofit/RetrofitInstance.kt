package com.example.myapplication.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

     val retrofitInstance by lazy{

        Retrofit.Builder().baseUrl("http://192.168.1.105:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    }
    val retrofitInstance2 by lazy{
        Retrofit.Builder().baseUrl("https://www.goldapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api2:ApiRetrofit by lazy{
        retrofitInstance2.create(ApiRetrofit::class.java)
    }

    val api:ApiRetrofit by lazy{
        retrofitInstance.create(ApiRetrofit::class.java)

    }
}