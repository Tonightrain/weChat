package com.example.weChat.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weChat.R
import com.example.weChat.adapter.MomentsAdapter
import com.example.weChat.util.MyApplication
import com.example.weChat.util.MyApplication.Companion.context
import com.example.weChat.viewModel.WeChatViewModel
import com.example.weChat.viewModel.WeChatViewModelFactory
import com.google.gson.GsonBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var profileAvatar: ImageView
    private lateinit var profileNick: TextView
//    private lateinit var imagesLinearLayout: LinearLayout
//    private lateinit var avatarView: ImageView
//    private lateinit var nickView: TextView
//    private lateinit var contentView: TextView

    private lateinit var weChatViewModel: WeChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImage = findViewById(R.id.profile_image)
        profileAvatar = findViewById(R.id.profile_avatar)
        profileNick = findViewById(R.id.profile_nick)
//        imagesLinearLayout = findViewById(R.id.moment_images)

//        avatarView = findViewById(R.id.moment_avatar)
//        nickView = findViewById(R.id.moment_nick)
//        contentView = findViewById(R.id.moment_content)

        weChatViewModel =
            ViewModelProvider(this, WeChatViewModelFactory()).get(WeChatViewModel::class.java)

        setProfileData()
        setMomentsData()
    }

    private fun loadPicture(url: String?, view: ImageView) {
        Glide.with(view.context).load(url).into(view)
    }

    private fun setProfileData() {
        weChatViewModel.loadProfileData()
        weChatViewModel.profileData.observe(this, { profileData ->
            loadPicture(profileData?.avatar, profileAvatar)
            loadPicture(profileData?.image, profileImage)
            profileNick.text = profileData?.nick
        })
    }

    private fun setMomentsData() {
        weChatViewModel.loadMomentsData()
        weChatViewModel.momentsData.observe(this, { momentsData ->

            val momentsView = findViewById<View>(R.id.moments) as RecyclerView
            val adapterMoment = MomentsAdapter(momentsData)
            momentsView.adapter = adapterMoment
            momentsView.layoutManager = LinearLayoutManager(context)


            //动态添加方法
//            val avatarView: ImageView? = findViewById(R.id.moment_avatar)
//            val nickView: TextView? = findViewById(R.id.moment_nick)
//            val contentView: TextView? = findViewById(R.id.moment_content)
//            val imageLinearLayout: LinearLayout? = findViewById(R.id.moment_images)

//            Log.d("moments1", GsonBuilder().setPrettyPrinting().create().toJson(momentsData))
//            momentsData.forEach { moment ->
//
//                Log.d("momenti", GsonBuilder().setPrettyPrinting().create().toJson(moment))
//
//                val avatarView: ImageView? = findViewById(R.id.moment_avatar)
//                val nickView: TextView? = findViewById(R.id.moment_nick)
//                val contentView: TextView? = findViewById(R.id.moment_content)
//
//                if (nickView != null) {
//                    nickView.text = moment.sender?.nick
//                }
//                if (moment.content != null) {
//                    if (contentView != null) {
//                        contentView.text = moment.content
//                    }
//                }
//
//                if (avatarView != null) {
//                    Glide.with(this).load(moment.sender?.avatar).into(avatarView)
//                }
//
//                moment.images?.forEach {
//                    val imageView = ImageView(context)
//                    imageView.layoutParams = ViewGroup.LayoutParams(300, 300)
//                    Glide.with(imageView).load(it).into(imageView)
//                    findViewById<LinearLayout?>(R.id.moment_images)?.addView(imageView)
////                    imageLinearLayout?.addView(imageView)
//                }
//
//                val view: View = layoutInflater.inflate(R.layout.moment_item, null)
//                linearLayout.addView(view)


        })
    }
}




