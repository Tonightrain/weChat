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
    private lateinit var weChatViewModel: WeChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        setProfileData()
        setMomentsData()
    }

    private fun initView() {
        profileImage = findViewById(R.id.profile_image)
        profileAvatar = findViewById(R.id.profile_avatar)
        profileNick = findViewById(R.id.profile_nick)

        weChatViewModel =
            ViewModelProvider(this, WeChatViewModelFactory()).get(WeChatViewModel::class.java)
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
        })
    }
}




