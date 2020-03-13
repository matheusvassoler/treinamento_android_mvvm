package com.example.aulamvvm.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aula3mvvn.model.User
import com.example.aulamvvm.R
import com.example.aulamvvm.interactor.FUserInteractor
import com.example.aulamvvm.interactor.OnFinishListener

class LoginViewModel(application: Application) : AndroidViewModel(application), OnFinishListener {
    //A view não enxerga a view model e nem deve enxergar
    //Quem emplementa a regra de negocio é a view model e a view tem que escutar
    //Toda vez que a property for alterada, a view tem exibir a mesma
    var statusMessage = MutableLiveData<String>() //Significa que pode ser escutado
    val contextApplication: Application = application

    //Variavel para controlar a exibição do progress bar
    var loading = MutableLiveData<Boolean>().apply { value = false }

    var shouldRedirect = MutableLiveData<Boolean>().apply { value = false }

    private var interactor: FUserInteractor = FUserInteractor(contextApplication)

    fun performLogin(username: String, password: String) {
        if(username.isEmpty() || password.isEmpty()) {
            statusMessage.value = contextApplication.resources.getString(R.string.invalid_user_or_password)
            return
        }
        loading.value = true
        interactor.performLogin(User(login=username, password = password), this)
    }

    override fun onResultSuccess(userData: User) {
        loading.value = false
        shouldRedirect.value = true
        interactor.saveLoggedUser(userData)
    }

    override fun onResultSuccess(userData: List<User>) {

    }

    override fun onResultFail(strErr: String) {
        statusMessage.value = strErr
        loading.value = false
    }
}
