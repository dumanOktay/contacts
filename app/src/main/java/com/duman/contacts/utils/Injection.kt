package com.duman.contacts.utils

import android.app.Application
import com.duman.contacts.data.UserRepository


object Injection {
    fun provideMoveRepository(app: Application) = UserRepository(app.applicationContext)
}