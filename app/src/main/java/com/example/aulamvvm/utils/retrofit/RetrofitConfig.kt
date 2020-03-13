package com.example.aula3mvvn.utils.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    //Companion object - Companion object simula o static do java, permitindo acessar a função através da classe
    //Sem instanciar um objeto
    companion object {
        //Path: recebe a url base da API
        fun getRetrofitInstance(path: String) : Retrofit {
            //Cria o objeto através do builder e retorna o mesmo
            return Retrofit.Builder()
                .baseUrl(path)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}