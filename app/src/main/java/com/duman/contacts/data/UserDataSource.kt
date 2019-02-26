package com.duman.contacts.data

import com.duman.contacts.model.Info
import com.duman.contacts.model.User

interface UserDataSource {

    interface GetUserListCallback {

        fun onUsersLoaded(list: List<Info>)

        fun onError()
    }


    interface GetUserCallback {
        /**
         * if email not found on data source
         */
        fun onUserNotFound()

        /**
         * if mail and  password are valid
         */
        fun onUserDataValid()

        /**
         * if mail is valid password invalid
         */
        fun onUserDataInvalid()

        /**
         * Case of System error such as Server error
         */
        fun onError()
    }

    interface GetUserDetailCallback{
        fun onOnUserLoaded(user: User)

        fun onError()
    }


    fun getContacts(callback: GetUserListCallback)

    /**
     * Checking given @email and @param password
     */
    fun getUser(email: String, password: String, callback: GetUserCallback)


    fun getUser(email: String, callback: GetUserDetailCallback)
    fun updateUser(user: User?,callback: GetUserDetailCallback)
}