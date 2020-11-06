package com.example.weChat.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weChat.R
import com.example.weChat.adapter.MomentsAdapter
import com.example.weChat.model.Moment
import com.example.weChat.model.Profile
import com.example.weChat.service.MomentsService
import com.example.weChat.service.ProfileService
import com.example.weChat.service.ServiceCreator
import com.example.weChat.util.MyApplication
import com.example.weChat.viewModel.WeChatViewModel
import com.example.weChat.viewModel.WeChatViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var profileAvatar: ImageView
    private lateinit var profileNick: TextView

    private lateinit var weChatViewModel: WeChatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImage = findViewById(R.id.profile_image)
        profileAvatar = findViewById(R.id.profile_avatar)
        profileNick = findViewById(R.id.profile_nick)

        weChatViewModel = ViewModelProvider(this, WeChatViewModelFactory()).get(WeChatViewModel::class.java)

        setProfileData()
        setMomentsData()

        //朋友圈列表recyclerView
//        moments = Moment.initData(10)
//        val momentsView = findViewById<View>(R.id.moments) as RecyclerView
//        val adapterMoment = MomentsAdapter(moments)
//        momentsView.adapter = adapterMoment
//        momentsView.layoutManager = LinearLayoutManager(this)

        //单条朋友圈
//        moments.forEach { moment ->
//            Log.d("images", "${moment.images.size} ")
//            gridView = findViewById(R.id.moment_images)
//            val mainAdapter = ImageGridViewAdapter(this@MainActivity,  moment.images)
//            gridView.adapter = mainAdapter
//        }

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
            momentsView.layoutManager = LinearLayoutManager(MyApplication.context)
        })
    }

}




