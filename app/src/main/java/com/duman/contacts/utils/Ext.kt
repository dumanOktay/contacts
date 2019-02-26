package com.duman.contacts.utils

import android.app.Activity
import android.util.Patterns
import android.support.design.widget.TextInputLayout
import android.databinding.BindingAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.duman.contacts.view.ViewModelFactory
import com.squareup.picasso.Picasso


fun String.isValidMail(): Boolean {

    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}


@BindingAdapter("errorText")
fun setErrorText(textInputLayout: TextInputLayout, id: Int?) {
    if (id != null && id != 0) {
        textInputLayout.error = textInputLayout.context.getString(id)
    } else {
        textInputLayout.error = ""
    }

}

fun Activity.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory.getInstance(application)
}


@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, url: String?) {
    url?:return
    Picasso.get().load(url).into(view)

}

fun View.getInflater(): LayoutInflater {
    return LayoutInflater.from(context)
}