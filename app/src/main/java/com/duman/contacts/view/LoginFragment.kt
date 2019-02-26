package com.duman.contacts.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duman.contacts.view.activities.MainActivity
import com.duman.contacts.databinding.LoginFragmentBinding
import com.duman.contacts.utils.getViewModelFactory

class LoginFragment : Fragment() {


    var binding: LoginFragmentBinding? = null

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, requireActivity().getViewModelFactory()).get(LoginViewModel::class.java)
        binding?.model = viewModel
        binding?.lifecycleOwner = this
        viewModel.loginCallback = object : LoginViewModel.OnLoginCallback {
            override fun onSuccess() {

                val mainActivity = requireActivity() as MainActivity
                mainActivity.replaceFragment(UserListFragment.newInstance())
            }

            override fun onError() {

            }
        }
    }

}
