package com.example.weChat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weChat.R
import com.example.weChat.model.Moment
import com.example.weChat.util.MyApplication
import kotlinx.android.synthetic.main.moment_item.view.*

class MomentsAdapter(private val moments: List<Moment>) :
    RecyclerView.Adapter<MomentsAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {

        val avatarView: ImageView = itemView.findViewById(R.id.moment_avatar)
        val nickView: TextView = itemView.findViewById(R.id.moment_nick)
        val contentView: TextView = itemView.findViewById(R.id.moment_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val momentView = inflater.inflate(R.layout.moment_item, parent, false)
        return ViewHolder(momentView)
    }


    override fun onBindViewHolder(viewHolder: MomentsAdapter.ViewHolder, position: Int) {
        val moment: Moment = moments[position]
        val avatar = viewHolder.avatarView
        val nick = viewHolder.nickView
        val content = viewHolder.contentView

        nick.text = moment.sender?.nick
        if (moment.content != null) {
            content.text = moment.content
        }
        Glide.with(MyApplication.context).load(moment.sender?.avatar).into(avatar)

    }

    override fun getItemCount(): Int {
        return moments.size
    }

}