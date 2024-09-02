package com.example.myapp.api

import com.example.myapp.model.User
import com.example.myapp.model.UserItem
import retrofit2.Response

import retrofit2.http.GET


interface Api {

   @GET("users")
    suspend fun getUsers(): Response<User>

}