package com.example.aulamvvm.userDetail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aula3mvvn.model.User

class UserDetailViewModelFactory constructor(private val application: Application, private val user: User): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(UserDetailViewModel::class.java) -> UserDetailViewModel(application, user)
                else -> {
                    throw IllegalArgumentException("Objeto não criado")
                }
            }
        } as T
    }
}