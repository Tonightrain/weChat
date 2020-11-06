package com.example.weChat.viewModel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weChat.R
import com.example.weChat.adapter.MomentsAdapter
import com.example.weChat.model.Moment
import com.example.weChat.model.Profile
import com.example.weChat.util.MyApplication
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeChatViewModel : ViewModel() {

    val profileData: LiveData<Profile>
        get() = _profileData

    private val _profileData = MutableLiveData<Profile>()

    val momentsData: LiveData<ArrayList<Moment>>
        get() = _momentsData

    private val _momentsData = MutableLiveData<ArrayList<Moment>>()

    private val weChatDBDataSource = MyApplication.weChatDBDataSource


    fun loadProfileData() {
        weChatDBDataSource.getProfileData().enqueue(object : Callback<Profile> {
            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                val profileData = response.body()

                _profileData.postValue(profileData)

//                loadPicture(profileData?.avatar, profileAvatar)
//                loadPicture(profileData?.image, profileImage)
//                profileNick.text = profileData?.nick
            }

            override fun onFailure(call: Call<Profile>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun loadMomentsData() {
        weChatDBDataSource.getMomentsData().enqueue(object : Callback<ArrayList<Moment>> {
            override fun onResponse(
                call: Call<ArrayList<Moment>>,
                response: Response<ArrayList<Moment>>
            ) {
                val momentsData = response.body()!!

                val momentsAfterFilter = momentsData.filter {
                    it.error == null
                } as ArrayList<Moment>
                _momentsData.postValue(momentsAfterFilter)
            }

            override fun onFailure(call: Call<ArrayList<Moment>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}