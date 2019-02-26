package com.duman.contacts.data

import android.content.Context
import android.os.Handler
import com.duman.contacts.model.Info
import com.duman.contacts.model.User
import com.duman.contacts.model.UserData
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import com.poilabs.poiutils.JsonData

class FirebaseUserRepository(val context: Context) : UserDataSource {


    var userData: UserData? = null

    var reference: DatabaseReference? = null
    var mailList = mutableListOf<String>()

    var userHashMap = HashMap<String, User>()

    init {
        JsonData.init(context)
        JsonData.DEBUG = false

        FirebaseApp.initializeApp(context)
        val database = FirebaseDatabase.getInstance()



        reference = database.getReference("userData")

        reference?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()) {
                    p0.getValue(UserData::class.java)?.let {
                        userData = it

                        userData?.userList?.forEach { user ->
                            mailList.add(user.info.email)
                            userHashMap[user.info.email] = user
                        }
                    }
                }
            }
        })


    }


    override fun getUser(email: String, callback: UserDataSource.GetUserDetailCallback) {
        userHashMap[email]?.let {
            callback.onOnUserLoaded(it)
        }
    }

    override fun getContacts(callback: UserDataSource.GetUserListCallback) {
        val infoList = mutableListOf<Info>()
        userData?.userList?.forEach {
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

        userData?.let {
            reference?.setValue(it)
            reference?.push()
        }


        Handler().postDelayed({ callback.onOnUserLoaded(user) }, 2000)
    }
}