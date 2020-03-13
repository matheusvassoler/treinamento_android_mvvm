package com.example.aulamvvm.utils.retrofit

import android.app.DownloadManager
import com.example.aula3mvvn.model.User
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class RequestLoginModel (
    var username: String,
    var password: String
)

data class ResponseError (
    @SerializedName("codigo_erro")
    var errorCode: Int,

    @SerializedName("descricao")
    var descricao: String
)


//Arquivo responsavel para ensinar o retrofit como acessar a API
interface UserApiInterface {
    //Diz para o retrofit que será enviado uma requisição do tipo post para o endpoint login com um body
    @POST("login")
    fun performLogin(@Body loginModel: RequestLoginModel): Call<User>

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUserDetail(@Path("id") id: Long): Call<User>

    @GET("users/{id}/parents")
    fun getUserRelatives(@Path("id") id: Long): Call<List<User>>
}