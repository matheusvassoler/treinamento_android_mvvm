package com.example.aulamvvm.userList

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class UserListViewModelFactory constructor(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(UserListViewModel::class.java) -> UserListViewModel(application)
                else -> {
                    throw IllegalArgumentException("Objeto não criado")
                }
            }
        } as T
    }
}