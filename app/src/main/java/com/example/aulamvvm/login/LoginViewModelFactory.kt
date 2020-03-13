package com.example.aulamvvm.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LoginViewModelFactory constructor(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return with(modelClass) {
            when {
                isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(application)
                else -> {
                    throw IllegalArgumentException("Objeto não criado")
                }
            }
        } as T
    }
}