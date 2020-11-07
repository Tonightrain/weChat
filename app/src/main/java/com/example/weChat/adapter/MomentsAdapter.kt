package com.example.weChat.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weChat.R
import com.example.weChat.model.Moment
import com.example.weChat.util.MyApplication.Companion.context

class MomentsAdapter(private val moments: List<Moment>) :
    RecyclerView.Adapter<MomentsAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        
        val avatarView: ImageView = itemView.findViewById(R.id.moment_avatar)
        val nickView: TextView = itemView.findViewById(R.id.moment_nick)
        val contentView: TextView = itemView.findViewById(R.id.moment_content)
        val linearLayoutImages: LinearLayout = itemView.findViewById(R.id.moment_images)
        val linearLayoutComments: LinearLayout = itemView.findViewById(R.id.moment_comments)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val momentView = inflater.inflate(R.layout.moment_item, parent, false)
        return ViewHolder(momentView)
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(viewHolder: MomentsAdapter.ViewHolder, position: Int) {
        val moment: Moment = moments[position]
        val avatar = viewHolder.avatarView
        val nick = viewHolder.nickView
        val content = viewHolder.contentView
        val linearLayoutImages = viewHolder.linearLayoutImages
        val linearLayoutComments = viewHolder.linearLayoutComments

        nick.text = moment.sender?.nick
        if (moment.content != null) {
            content.text = moment.content
        }
        Glide.with(context).load(moment.sender?.avatar).into(avatar)

        moment.images?.forEach {
            val imageView = ImageView(context)
            imageView.layoutParams = ViewGroup.LayoutParams(300, 300)
            Glide.with(imageView).load(it).into(imageView)
            linearLayoutImages.addView(imageView)
        }

        moment.comments?.forEach { comment ->

            val viewComment: View =
                LayoutInflater.from(context).inflate(R.layout.comment_item, null)
            val commentNick: TextView = viewComment.findViewById(R.id.comment_nick)
            val commentContent: TextView = viewComment.findViewById(R.id.comment_content)

            commentNick.text = comment.sender.nick
            commentContent.text = ": ${comment.content}"
            linearLayoutComments.addView(viewComment)

        }

    }

    override fun getItemCount(): Int {
        return moments.size
    }

}