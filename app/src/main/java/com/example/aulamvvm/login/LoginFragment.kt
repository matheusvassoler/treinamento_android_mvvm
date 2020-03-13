package com.example.aulamvvm.login

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer

import com.example.aulamvvm.R
import com.example.aulamvvm.databinding.LoginFragmentBinding
import com.example.aulamvvm.userList.UserListFragment
import com.example.aulamvvm.utils.GenericFragment
import com.example.aulamvvm.utils.extensions.inTransaction

class LoginFragment : GenericFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        val factory = LoginViewModelFactory(activity?.application!!)
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        bindingView()
        return binding.root
//        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun bindingView() {
        //val cardViewButton : CardView = findViewById(R.id.buttom)
//        var btn = buttom;
//        var username = txtUsername
//        buttom.setOnClickListener(clickLoginButton)

        binding.viewModel?.statusMessage?.observe(viewLifecycleOwner, Observer{
            showMessage(it)
        })

        binding.viewModel?.shouldRedirect?.observe(viewLifecycleOwner, Observer{
            if(it) {
                showListeUsersScreen()
            }
        })
    }

    private fun showListeUsersScreen() {
        activity?.supportFragmentManager!!.inTransaction {
            add(R.id.container_fragment, UserListFragment())
        }
    }

}
