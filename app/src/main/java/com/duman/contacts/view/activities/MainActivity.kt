package com.duman.contacts.view.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.duman.contacts.R
import com.duman.contacts.model.UserData
import com.duman.contacts.view.LoginFragment
import com.google.gson.Gson
import com.poilabs.poiutils.JsonData

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivityÇççç"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginFragment = LoginFragment.newInstance()
        replaceFragment(loginFragment)
    }

    fun replaceFragment(loginFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, loginFragment)
            .commitNow()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "ondestoyy " + TAG)

    }


}
