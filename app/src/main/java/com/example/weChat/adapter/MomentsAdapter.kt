package com.example.weChat.adapter

import android.annotation.SuppressLint
import android.opengl.Visibility
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
        val linearLayoutImagesLine2: LinearLayout = itemView.findViewById(R.id.moment_images_2)

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
        val linearLayoutImagesLine2 = viewHolder.linearLayoutImagesLine2

        nick.text = moment.sender?.nick
        if (moment.content != null) {
            content.text = moment.content
        } else {
            content.visibility = View.GONE
        }
        Glide.with(context).load(moment.sender?.avatar).into(avatar)

        if (moment.images != null) {
            when {
                moment.images?.size!! in 4..6 -> {
                    val imagesLine1 = moment.images!!.slice(listOf(0, 1, 2))
                    loadImages(imagesLine1, linearLayoutImages, false)

                    val imagesLine2 = moment.images!!.slice(listOf(3))
                    loadImages(imagesLine2, linearLayoutImagesLine2, false)
                }
                moment.images!!.size == 1 -> {
                    loadImages(moment.images!!, linearLayoutImages, true)
                }
                else -> {
                    loadImages(moment.images!!, linearLayoutImages, false)
                }
            }
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

    private fun loadImages(
        imageList: List<String>,
        linearLayout: LinearLayout,
        isOneImage: Boolean
    ) {

        imageList.forEach {
            val imageView = ImageView(context)

            if (isOneImage) {
                imageView.layoutParams = LinearLayout.LayoutParams(300, 500)
                Glide.with(imageView).load(it).centerCrop().into(imageView)
            } else {
                imageView.layoutParams = LinearLayout.LayoutParams(250, 250)
                Glide.with(imageView).load(it).override(80, 80).centerCrop().into(imageView)
            }

            val imageViewParams: LinearLayout.LayoutParams =
                imageView.layoutParams as LinearLayout.LayoutParams
            imageViewParams.setMargins(20, 0, 0, 0)
            imageView.layoutParams = imageViewParams

            linearLayout.addView(imageView)
        }
    }
}