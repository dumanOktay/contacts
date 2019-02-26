package com.duman.contacts.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.duman.contacts.data.UserDataSource
import com.duman.contacts.model.Info

class UserListViewModel(dataSource: UserDataSource) : ViewModel(), UserDataSource.GetUserListCallback {

    var liveData = MutableLiveData<List<Info>>()
    override fun onUsersLoaded(list: List<Info>) {

        liveData.value = list
    }

    override fun onError() {

    }

    init {
        dataSource.getContacts(this)
    }
}
