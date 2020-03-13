package com.example.aulamvvm.userDetail

import android.app.Application
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aula3mvvn.model.User
import com.example.aulamvvm.interactor.FUserInteractor
import com.example.aulamvvm.interactor.OnFinishListener
import com.squareup.picasso.Picasso

class UserDetailViewModel constructor(application: Application, val selectedUser: User) : AndroidViewModel(application), OnFinishListener {

    var loading = MutableLiveData<Boolean>().apply { value = false }
    var relativeDataSource: MutableLiveData<List<User>> = MutableLiveData<List<User>>()
    private var interactor: FUserInteractor = FUserInteractor(application)
    var strPhone: MutableLiveData<String> = MutableLiveData<String>()

    fun getUserDetails() {
        loading.value = true
        interactor.getUserDetail(selectedUser, this)
    }

    fun getUserRelatives() {
        loading.value = true
        interactor.getUserRelatives(selectedUser.userId, this)
    }

    fun configView(user_image: ImageView) {
        Picasso.get().load(selectedUser.imgUrl).into(user_image)
    }

    override fun onResultSuccess(userData: User) {
        loading.value = false
        selectedUser.phone = userData.phone
        strPhone.value = "Telefone: ${userData.phone}"
    }

    override fun onResultSuccess(userData: List<User>) {
        loading.value = false
        relativeDataSource.value = userData
    }

    override fun onResultFail(strError: String) {
        loading.value = false
    }

}
