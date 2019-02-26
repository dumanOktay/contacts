package com.duman.contacts.data

import android.content.Context
import android.os.Handler
import com.duman.contacts.model.Info
import com.duman.contacts.model.User
import com.duman.contacts.model.UserData
import com.google.gson.Gson
import com.poilabs.poiutils.JsonData

class UserRepository(val context: Context) : UserDataSource {


    lateinit var userData: UserData

    var mailList = mutableListOf<String>()

    var userHashMap = HashMap<String, User>()

    init {
        JsonData.init(context)
        JsonData.DEBUG = false

        initUserData()
        userData = UserData.getInstance()
        for (user in userData.userList) {
            mailList.add(user.info.email)
            userHashMap[user.info.email] = user
        }
    }


    private fun initUserData() {
        if (UserData.getInstance().userList.isEmpty()) {
            val json_string = context.assets.open("users.json").bufferedReader().use {
                it.readText()

            }

            Gson().fromJson(json_string, UserData::class.java).also {
                it.save()
            }

        }
    }

    override fun getUser(email: String, callback: UserDataSource.GetUserDetailCallback) {
        userHashMap[email]?.let {
            callback.onOnUserLoaded(it)
        }
    }

    override fun getContacts(callback: UserDataSource.GetUserListCallback) {
        val infoList = mutableListOf<Info>()
        userData.userList.forEach {
            infoList.add(it.info)
        }
        callback.onUsersLoaded(infoList)
    }

    override fun getUser(email: String, password: String, callback: UserDataSource.GetUserCallback) {
        userHashMap[email.trim()]?.password?.let {
            if (it.equals(password, false)) {
                Handler().postDelayed({ callback.onUserDataValid() }, 1000)
                return
            } else {
                Handler().postDelayed({ callback.onUserDataInvalid() }, 1000)
                return
            }
        }
        Handler().postDelayed({ callback.onUserNotFound() }, 1000)
        return
    }

    override fun updateUser(user: User?, callback: UserDataSource.GetUserDetailCallback) {
        user ?: return

        userData.save()

        Handler().postDelayed({ callback.onOnUserLoaded(user) }, 2000)
    }
}