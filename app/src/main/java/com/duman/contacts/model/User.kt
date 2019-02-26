package com.duman.contacts.model

import com.poilabs.poiutils.JsonData

data class User(
    val info: Info
    , val old: Int
    , val address: String
    , val password: String = ""
    , var phone: String = ""
)


data class Info(
    val name: String
    , var email: String
    , val image: String
)


data class UserData private constructor(var userList: List<User>) : JsonData() {

    override fun save() {
        save(FILE_NAME)
    }

    override fun delete() {
        delete(FILE_NAME)
    }

    companion object {
        private const val FILE_NAME = "android.service.autofill.UserData.json"
        fun getInstance(): UserData {
            return getIns(FILE_NAME) ?: UserData(mutableListOf())
        }
    }
}