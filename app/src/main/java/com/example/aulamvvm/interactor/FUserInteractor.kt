package com.example.aulamvvm.interactor

import android.app.Activity
import android.app.Application
import android.os.AsyncTask
import android.util.Log.d
import com.example.aula3mvvn.model.User
import com.example.aula3mvvn.utils.retrofit.RetrofitConfig
import com.example.aulamvvm.utils.AppExecutors
import com.example.aulamvvm.utils.retrofit.RequestLoginModel
import com.example.aulamvvm.utils.retrofit.ResponseError
import com.example.aulamvvm.utils.retrofit.UserApiInterface
import com.example.aulamvvm.utils.room.AppDatabase
import com.example.aulamvvm.utils.room.entities.UserEntity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

interface OnFinishListener {
    fun onResultSuccess(userData: User)
    fun onResultSuccess(userData: List<User>)
    fun onResultFail(strError: String)
}

class FUserInteractor(application: Application) {
    var contextApplication: Application = application
    val appExecutors: AppExecutors = AppExecutors()
    val retrofitClient = RetrofitConfig.getRetrofitInstance("http://712b65d2.ngrok.io")

    //O model é utilizado para transitar dados entre todas as camadas
    fun performLogin(user: User, onFinishListener: OnFinishListener) {

        val endpoint = retrofitClient.create(UserApiInterface::class.java)
        val callback = endpoint.performLogin(RequestLoginModel(user.login, user.password))

        callback.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onFinishListener.onResultFail(t.message.toString())
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    val userData = response.body() as User
                    onFinishListener.onResultSuccess(userData)
                } else {
                    var gson = Gson()
                    var responseError = gson.fromJson(response.errorBody()?.charStream(), ResponseError::class.java)
                    onFinishListener.onResultFail(responseError.descricao)
                }
            }

        })
    }

    fun getUserDetail(user: User, onFinishListener: OnFinishListener) {

        val endpoint = retrofitClient.create(UserApiInterface::class.java)
        val callback = endpoint.getUserDetail(user.userId)

        callback.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onFinishListener.onResultFail(t.message.toString())
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful) {
                    val userData = response.body() as User
                    onFinishListener.onResultSuccess(userData)
                } else {
                    var gson = Gson()
                    var responseError = gson.fromJson(response.errorBody()?.charStream(), ResponseError::class.java)
                    onFinishListener.onResultFail(responseError.descricao)
                }
            }

        })
    }

    fun getUserRelatives(userId: Long, onFinishListener: OnFinishListener) {

        val endpoint = retrofitClient.create(UserApiInterface::class.java)
        val callback = endpoint.getUserRelatives(userId)

        callback.enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                onFinishListener.onResultFail(t.message.toString())
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful) {
                    val userData = response.body() as List<User>
                    onFinishListener.onResultSuccess(userData)
                } else {
                    var gson = Gson()
                    var responseError = gson.fromJson(response.errorBody()?.charStream(), ResponseError::class.java)
                    onFinishListener.onResultFail(responseError.descricao)
                }
            }

        })
    }

    fun getUsers(onFinishListener: OnFinishListener) {

        val endpoint = retrofitClient.create(UserApiInterface::class.java)
        val callback = endpoint.getUsers()

        callback.enqueue(object: Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                onFinishListener.onResultFail(t.message.toString())
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful) {
                    val userData = response.body() as List<User>
                    onFinishListener.onResultSuccess(userData)
                } else if(response.code() == 404) {
                    onFinishListener.onResultFail("API NOT FOUND")
                } else {
                    var gson = Gson()
                    var responseError = gson.fromJson(response.errorBody()?.charStream(), ResponseError::class.java)
                    onFinishListener.onResultFail(responseError.descricao)
                }
            }

        })
    }

    fun saveLoggedUser(userData: User) {
        //Executa o bloco de codigo em background
        appExecutors.roomIOThreadExecutor.execute {
            val userDao = AppDatabase.getInstance(contextApplication).userDao()
            val userEntity = userData.getUserEntity()
            userEntity.isLogged = true
            val id = userDao.add(userEntity)
        }
    }

    fun getLoggedUser(success: (UserEntity?) -> Unit, failure: () -> Unit) {
        appExecutors.roomIOThreadExecutor.execute {
            val userDao = AppDatabase.getInstance(contextApplication).userDao()
            var loggedUser: UserEntity? = null
            try {
                var loggedUser = userDao.logedUser()
                appExecutors.mainThreadExecutor.execute{success(loggedUser)}
            } catch (e: Exception) {
                appExecutors.mainThreadExecutor.execute{failure()}
            }
        }
    }
}
