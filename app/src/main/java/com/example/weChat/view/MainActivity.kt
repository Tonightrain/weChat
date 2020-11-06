package com.example.weChat.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var profileService: ProfileService
    private lateinit var profileImage: ImageView
    private lateinit var profileAvatar: ImageView
    private lateinit var profileNick: TextView
    private val profileDataError = "load profile data error occurred"
    private val momentsDataError = "load moments data error occurred"
    private lateinit var moments: ArrayList<Moment>
    private lateinit var momentsAfterFilter: List<Moment>
    private lateinit var momentsService: MomentsService
    private lateinit var gridView: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileService = ServiceCreator.create(ProfileService::class.java)
        profileImage = findViewById(R.id.profile_image)
        profileAvatar = findViewById(R.id.profile_avatar)
        profileNick = findViewById(R.id.profile_nick)

        profileService.getProfileData().enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                val profileData = response.body()

                Log.d("profile", "${profileData?.image}")

                loadPicture(profileData?.avatar, profileAvatar)
                profileNick.text = profileData?.nick
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Toast.makeText(this@MainActivity, profileDataError, Toast.LENGTH_SHORT)
                    .show()
            }
        })

        momentsService = ServiceCreator.create(MomentsService::class.java)
        momentsService.getMomentsData().enqueue(object : Callback<ArrayList<Moment>> {
            override fun onResponse(
                call: Call<ArrayList<Moment>>,
                response: Response<ArrayList<Moment>>
            ) {
                moments = response.body()!!

                momentsAfterFilter = moments.filter {
                    it.error == null
                }

                momentsAfterFilter.forEach {
                    Log.d("moment", "${it.content}")
                }

                val momentsView = findViewById<View>(R.id.moments) as RecyclerView
                val adapterMoment = MomentsAdapter(momentsAfterFilter)
                momentsView.adapter = adapterMoment
                momentsView.layoutManager = LinearLayoutManager(MyApplication.context)
            }
            override fun onFailure(call: Call<ArrayList<Moment>>, t: Throwable) {
                Toast.makeText(this@MainActivity, momentsDataError, Toast.LENGTH_SHORT)
                    .show()
            }
        })


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

    fun loadPicture(url: String?, view: ImageView) {
        Glide.with(view.context).load(url).into(view)
    }
}




