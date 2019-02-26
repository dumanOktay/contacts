package com.duman.contacts.view

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.duman.contacts.data.UserDataSource
import com.duman.contacts.utils.Injection

class ViewModelFactory private constructor(private val userDataSource: UserDataSource) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(userDataSource)
            isAssignableFrom(UserListViewModel::class.java) -> UserListViewModel(userDataSource)
            isAssignableFrom(UserDetailViewModel::class.java) -> UserDetailViewModel(userDataSource)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(app: Application) = INSTANCE ?: synchronized(ViewModelFactory::class.java) {
            INSTANCE ?: ViewModelFactory(Injection.provideMoveRepository(app)).also {
                INSTANCE = it
            }
        }
    }
}