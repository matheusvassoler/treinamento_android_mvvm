package com.example.aulamvvm.userList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aula3mvvn.model.User
import com.example.aulamvvm.interactor.FUserInteractor
import com.example.aulamvvm.interactor.OnFinishListener

class UserListViewModel(application: Application) : AndroidViewModel(application),
    OnFinishListener {

    //Variavel para controlar a exibição do progress bar
    var loading         = MutableLiveData<Boolean>().apply { value = false }
    val contextApplication: Application         = application
    private var interactor: FUserInteractor     = FUserInteractor(contextApplication)
    var dataSource: MutableLiveData<List<User>> = MutableLiveData<List<User>>()

    fun performTest() {

    }

    fun getUsers() {
        loading.value = true
        interactor.getUsers(this)
    }

    override fun onResultSuccess(userData: User) {
        loading.value = false
    }

    override fun onResultSuccess(userData: List<User>) {
        loading.value = false
        dataSource.value = userData
    }

    override fun onResultFail(strError: String) {
        loading.value = false
    }

}
