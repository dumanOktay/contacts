package com.duman.contacts.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.duman.contacts.R
import com.duman.contacts.model.Info
import com.duman.contacts.utils.getInflater
import com.duman.contacts.view.activities.UserDetailActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_adapter.view.*

class UserAdapter(var list: List<Info>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(p0.getInflater().inflate(R.layout.user_adapter, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.bind(list[p1])
    }

    fun update(userList: List<Info>) {
        list = userList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(info: Info) = with(itemView) {
            Picasso.get().load(info.image).into(img)
            title.text = info.name
            setOnClickListener {

                UserDetailActivity.startNewInstance(info.email, context)
            }
        }
    }
}