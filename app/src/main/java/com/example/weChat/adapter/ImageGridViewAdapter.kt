package com.example.weChat.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bumptech.glide.Glide
import com.example.weChat.R


internal class ImageGridViewAdapter(
    private val context: Context,
    private val numberImage: Array<String>
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView

    override fun getCount(): Int {
        return numberImage.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.image_item, null)
        }
        imageView = convertView!!.findViewById(R.id.moment_image)
        Glide.with(imageView).load(numberImage[position]).into(imageView)
        return convertView
    }
}