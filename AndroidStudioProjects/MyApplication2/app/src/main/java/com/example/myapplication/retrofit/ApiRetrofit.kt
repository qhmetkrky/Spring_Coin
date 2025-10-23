package com.example.myapplication.retrofit

import androidx.annotation.GravityInt
import com.example.myapplication.data.GoldPricesResponse
import com.example.myapplication.data.PriceDateRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiRetrofit {

    @POST("/register")
    suspend fun saveUser(@Body user: User): Response<User>

    @POST("/amount")
    suspend fun getAmount(@Body amount: Double): Response<Double>

    @POST("/login")
    suspend fun login(@Body login:Login_user): Response<Login_response>

    @PUT("/amount")
    suspend fun updateAmount(
        @Header("Authorization") token: String,
        @Body amount: Map<String, Double>
    ): Response<Unit>




    @GET("api/{symbol}/{currency}/{date}")
    @Headers("x-access-token: goldapi-45fkid19mgr4qydd-io")
    suspend fun getGoldPrice(
        @Path("symbol") symbol: String,      // Örnek: "XAU"
        @Path("currency") currency: String,  // Örnek: "TRY"
        @Path("date") date: String? = ""
    ) : Response<request>



}