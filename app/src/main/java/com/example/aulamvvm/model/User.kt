package com.example.aula3mvvn.model

import com.example.aulamvvm.utils.room.entities.UserEntity
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class User (
    @SerializedName("id")
    var userId: Long = 0,

    @SerializedName("name")
    var name: String = "",

    @SerializedName("address")
    var address: String = "",

    @SerializedName("phone")
    var phone: String = "",

    @SerializedName("image")
    var imgUrl: String = "",

    //Quando o nome do objeto é diferente do que está na API, é necessário inserir o SerializedName com o valor que está na API
    @SerializedName("login")
    var login: String = "",

    var password: String = ""
) {
    fun getUserEntity(): UserEntity {
        return UserEntity(
            login    = this.login,
            address  = this.address,
            imgUrl   = this.imgUrl,
            isLogged = false,
            name     = this.name,
            userId   = this.userId.toLong()
        )
    }
}