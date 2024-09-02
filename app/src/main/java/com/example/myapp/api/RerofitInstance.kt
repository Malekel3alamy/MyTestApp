package com.example.myapp.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



abstract class RetrofitInstance {
companion object{
    val client = OkHttpClient.Builder().build()

    val retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(Api::class.java)
}

}