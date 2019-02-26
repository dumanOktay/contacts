package com.duman.contacts.model

import com.google.firebase.database.IgnoreExtraProperties
import com.poilabs.poiutils.JsonData

@IgnoreExtraProperties
class User(

    val info: Info = Info()
    , val old: Int = 0
    , val address: String = ""
    , val password: String = ""
    , var phone: String = ""
) {
    constructor() : this(Info())
}

@IgnoreExtraProperties
data class Info(
    val name: String = ""
    , var email: String = ""
    , val image: String = ""
)

@IgnoreExtraProperties
data class UserData(var userList: List<User>) : JsonData() {

    constructor() : this(mutableListOf())

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