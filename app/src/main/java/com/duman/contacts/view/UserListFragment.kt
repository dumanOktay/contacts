package com.duman.contacts.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.duman.contacts.R
import com.duman.contacts.utils.getViewModelFactory
import com.duman.contacts.view.adapters.UserAdapter
import kotlinx.android.synthetic.main.user_list_fragment.*

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var viewModel: UserListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user_list.layoutManager = LinearLayoutManager(requireContext())
        val userAdapter = UserAdapter(mutableListOf())
        user_list.adapter = userAdapter
        viewModel =
            ViewModelProviders.of(this, requireActivity().getViewModelFactory()).get(UserListViewModel::class.java)
                .apply {
                    liveData.observe(this@UserListFragment, Observer {
                        it?.let { it1 -> userAdapter.update(it1) }
                    })
                }
    }

}
