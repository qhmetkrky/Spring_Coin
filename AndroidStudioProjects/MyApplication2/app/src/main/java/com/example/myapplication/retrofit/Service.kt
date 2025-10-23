package com.example.myapplication.retrofit

import retrofit2.Response

class Service {

    suspend fun getPost(user:User): Response<User>{
        return RetrofitInstance.api.saveUser(user)

    }
}