package com.example.aulamvvm.userDetail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.aula3mvvn.model.User

import com.example.aulamvvm.R
import com.example.aulamvvm.databinding.UserDetailFragmentBinding
import com.example.aulamvvm.utils.GenericFragment
import kotlinx.android.synthetic.main.user_detail_fragment.*

class UserDetailFragment : GenericFragment() {

    companion object {
        fun newInstance() = UserDetailFragment()
    }

    private lateinit var viewModel: UserDetailViewModel
    private lateinit var selecteduser: User
    private lateinit var binding: UserDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.user_detail_fragment, container, false)
        val factory = UserDetailViewModelFactory(activity?.application!!, selecteduser)
        viewModel = ViewModelProviders.of(this, factory).get(UserDetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        viewModel.getUserDetails()
        viewModel.getUserRelatives()
        bindingView()
        return binding.root
        //return inflater.inflate(R.layout.user_detail_fragment, container, false)
    }

    fun setSelectedUser(user: User) {
        selecteduser = user
    }

    fun bindingView() {
        binding.viewModel?.strPhone?.observe(viewLifecycleOwner, Observer {
            binding.txtPhone.text = it
        })

        binding.viewModel?.relativeDataSource?.observe(viewLifecycleOwner, Observer {
            it?.let {relativesList ->
                with(relatives) {
                    layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity?.application,RecyclerView.VERTICAL,false)
                    setHasFixedSize(true)
                    adapter = RelativeAdapter(relativesList)
                }
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.configView(binding.userImage)
    }

}
