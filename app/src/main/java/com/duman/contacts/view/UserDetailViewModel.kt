package com.duman.contacts.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableInt
import android.view.View
import com.duman.contacts.data.UserDataSource
import com.duman.contacts.model.User
import com.duman.contacts.utils.isValidMail
import android.content.Intent
import android.net.Uri


class UserDetailViewModel(private var dataSource: UserDataSource) : ViewModel(),
    UserDataSource.GetUserDetailCallback {

    private var mCurrentUser: User? = null
    var email = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var progressVisibility = ObservableInt(View.GONE)
    var imageUrl = MutableLiveData<String>()
    var phone = MutableLiveData<String>()
    var address = MutableLiveData<String>()

    override fun onOnUserLoaded(user: User) {
        progressVisibility.set(View.GONE)
        mCurrentUser = user
        email.value = user.info.email
        phone.value = user.password
        address.value = user.address
        imageUrl.value = user.info.image
        name.value = user.info.name
    }

    fun getUser(email: String) {
        progressVisibility.set(View.VISIBLE)
        dataSource.getUser(email, this)
    }

    fun updateClick(view: View) {
        val emailValue = email.value
        if (emailValue?.isValidMail() == true) {
            mCurrentUser?.info?.email = emailValue
        }

        val addressValue = address.value
        if (addressValue != null) {
            mCurrentUser?.address = addressValue
        }
        progressVisibility.set(View.VISIBLE)
        dataSource.updateUser(mCurrentUser,this)

    }

    fun onMailClick(view: View){
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:"+mCurrentUser?.info?.email)
        view.context.startActivity(emailIntent)
    }

    override fun onError() {

    }
}