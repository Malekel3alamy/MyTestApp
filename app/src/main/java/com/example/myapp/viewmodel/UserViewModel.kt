package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.api.RetrofitInstance
import com.example.myapp.model.User
import com.example.myapp.model.UserItem
import com.example.myapp.utils.Resources
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {


    val usersList = MutableSharedFlow<List<UserItem>>()

    fun getUsers() = viewModelScope.launch {

        val reponse = RetrofitInstance.api.getUsers().body()
        usersList.emit(reponse!!)
    }
}