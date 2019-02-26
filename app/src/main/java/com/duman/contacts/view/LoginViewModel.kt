package com.duman.contacts.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableInt
import android.view.View
import com.duman.contacts.R
import com.duman.contacts.data.UserDataSource
import com.duman.contacts.utils.isValidMail

class LoginViewModel(private var dataSource: UserDataSource) : ViewModel(), UserDataSource.GetUserCallback {
    var email = MutableLiveData<String>()
    var passWord = MutableLiveData<String>()
    var emailError = ObservableInt()
    var progressVisibility = ObservableInt(View.GONE)
    var passwordError = ObservableInt()
    var loginCallback: OnLoginCallback? = null

    fun onLoginClick() {
        if (email.value?.isValidMail() == false) {
            passwordError.set(0)
            emailError.set(R.string._error_check_warning)
            return
        }
        val passLength = passWord.value?.length ?: 0
        if (passLength < 5) {
            emailError.set(0)
            passwordError.set(R.string._password_not_valid)
            return
        }
        progressVisibility.set(View.VISIBLE)
        dataSource.getUser(email.value ?: "", passWord.value ?: "", this)
    }


    override fun onUserNotFound() {
        progressVisibility.set(View.GONE)
        passwordError.set(0)
        emailError.set(R.string._user_not_found)
    }

    override fun onUserDataValid() {
        progressVisibility.set(View.GONE)
        loginCallback?.onSuccess()
    }

    override fun onUserDataInvalid() {
        progressVisibility.set(View.GONE)
        emailError.set(0)
        passwordError.set(R.string._password_not_valid)
    }

    override fun onError() {
        progressVisibility.set(View.GONE)
        emailError.set(R.string._server_error)
    }

    interface OnLoginCallback {
        fun onSuccess()

        fun onError()
    }
}
