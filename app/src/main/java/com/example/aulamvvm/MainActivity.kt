package com.example.aulamvvm

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.aulamvvm.interactor.FUserInteractor
import com.example.aulamvvm.login.LoginFragment
import com.example.aulamvvm.userList.UserListFragment
import com.example.aulamvvm.utils.extensions.inTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var userInteractor: FUserInteractor = FUserInteractor(applicationContext as Application)
        var fragment: Fragment = LoginFragment()

        userInteractor.getLoggedUser ({loggedUser ->
            if(loggedUser != null) {
                fragment = UserListFragment()
            }
            showScreen(fragment)
        }, {

        })


    }

    fun showScreen(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            add(R.id.container_fragment, fragment)
        }
    }
}
