package com.example.weChat.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.weChat.R
import com.example.weChat.adapter.MomentsAdapter
import com.example.weChat.model.Moment
import com.example.weChat.util.MyApplication
import com.example.weChat.util.MyApplication.Companion.context
import com.example.weChat.viewModel.WeChatViewModel
import com.example.weChat.viewModel.WeChatViewModelFactory
import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import org.w3c.dom.Text
import kotlin.math.ceil


private const val FIRST_NUMBER = 5
private const val SECOND_NUMBER = 10
private const val LAST_NUMBER = 15

class MainActivity : AppCompatActivity() {

    private lateinit var profileImage: ImageView
    private lateinit var profileAvatar: ImageView
    private lateinit var profileNick: TextView
    private lateinit var weChatViewModel: WeChatViewModel
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var sliceMomentData: List<Moment>
    private lateinit var nestedView: NestedScrollView


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
        swipeRefresh = findViewById(R.id.swipeRefreshLayout)
        nestedView = findViewById(R.id.nestedScrollView)

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

    @SuppressLint("ResourceAsColor")
    private fun setMomentsData() {
        weChatViewModel.loadMomentsData()
        weChatViewModel.momentsData.observe(this, { momentsData ->

            setViewData(momentsData, listOf(0, 1, 2, 3, 4))

            swipeRefresh.setOnRefreshListener {
                setViewData(momentsData, listOf(0, 1, 2, 3, 4))
                swipeRefresh.isRefreshing = false
            }

            nestedView.setOnScrollChangeListener { scrollview: NestedScrollView, _: Int, y: Int, _: Int, _: Int ->
                if (y == (scrollview.getChildAt(0).measuredHeight) - scrollview.measuredHeight) {

                    when (sliceMomentData.size) {
                        FIRST_NUMBER -> {
                            setViewData(momentsData, listOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
                            Toast.makeText(this, R.string.load_message, Toast.LENGTH_SHORT).show()
                        }

                        SECOND_NUMBER -> {
                            setViewData(
                                momentsData, listOf(
                                    0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14
                                )
                            )
                            Toast.makeText(this, R.string.load_message, Toast.LENGTH_SHORT).show()
                        }
                        LAST_NUMBER -> {
                            setMomentsDataAdapter(momentsData)
                            Toast.makeText(this, R.string.load_message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })
    }

    private fun setMomentsDataAdapter(data: List<Moment>) {
        val momentsView = findViewById<View>(R.id.moments) as RecyclerView
        val adapterMoment = MomentsAdapter(data)
        momentsView.adapter = adapterMoment
        momentsView.layoutManager = LinearLayoutManager(context)
    }

    private fun setViewData(momentsData: ArrayList<Moment>, list: List<Int>) {
        sliceMomentData = momentsData.slice(list)
        setMomentsDataAdapter(sliceMomentData)
    }

}




