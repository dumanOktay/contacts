package com.duman.contacts.view.activities

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.duman.contacts.R
import com.duman.contacts.databinding.ActivityUserDetailBinding
import com.duman.contacts.utils.getViewModelFactory
import com.duman.contacts.view.UserDetailViewModel
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity() {

    var viewModel: UserDetailViewModel? = null
    lateinit var binding: ActivityUserDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        val mail = intent.getStringExtra(EMAIL_KEY)
        setSupportActionBar(toolbar)

        toolbar_layout.setExpandedTitleColor(Color.TRANSPARENT)


        viewModel = ViewModelProviders.of(this, getViewModelFactory()).get(UserDetailViewModel::class.java).also {
            binding.model = it
            binding.lifecycleOwner = this

        }

        viewModel?.getUser(mail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        private const val EMAIL_KEY = "email"

        fun startNewInstance(email: String, context: Context) {
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra(EMAIL_KEY, email)
            context.startActivity(intent)

        }
    }
}
