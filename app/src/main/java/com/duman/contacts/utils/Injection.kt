package com.duman.contacts.utils

import android.app.Application
import com.duman.contacts.data.FirebaseUserRepository
import com.duman.contacts.data.UserRepository


object Injection {

    /**
     * work with Local Data
     */
//    fun provideMoveRepository(app: Application) = UserRepository(app.applicationContext)

    /**
     * work with Firebase data
     */
    fun provideMoveRepository(app: Application) = FirebaseUserRepository(app.applicationContext)
}