package com.example.aulamvvm.userList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aula3mvvn.model.User

import com.example.aulamvvm.R
import com.example.aulamvvm.databinding.UserListFragmentBinding
import com.example.aulamvvm.login.LoginViewModel
import com.example.aulamvvm.login.LoginViewModelFactory
import com.example.aulamvvm.userDetail.UserDetailFragment
import com.example.aulamvvm.utils.extensions.inTransaction
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var viewModel: UserListViewModel
    private lateinit var binding: UserListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_list_fragment, container, false)
        binding.toolbar.title = "Usuários"
        val factory = UserListViewModelFactory(activity?.application!!)
        viewModel = ViewModelProviders.of(this, factory).get(UserListViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getUsers()
        bindingView()
        return binding.root
        //return inflater.inflate(R.layout.login_fragment, container, false)
    }

    fun bindingView() {
        binding.viewModel?.dataSource?.observe(viewLifecycleOwner, Observer {
            with(user_list) {
                layoutManager = LinearLayoutManager(activity?.application!!, RecyclerView.VERTICAL, false)
                //it = users
                adapter = UserAdapter(it) {
                    activity?.supportFragmentManager!!.inTransaction {
                        addToBackStack("UserList")
                        val userDetailFragment = UserDetailFragment()
                        userDetailFragment.setSelectedUser(it)
                        add(R.id.container_fragment, userDetailFragment)
                    }
                }
                setHasFixedSize(true)
                //addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
