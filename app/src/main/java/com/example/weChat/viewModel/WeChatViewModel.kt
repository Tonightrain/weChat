package com.example.weChat.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weChat.model.Moment
import com.example.weChat.model.Profile
import com.example.weChat.util.MyApplication
import com.google.gson.GsonBuilder
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
                    it.error == null && it.unknownError == null

                }
                _momentsData.postValue(momentsAfterFilter as ArrayList<Moment>?)
            }

            override fun onFailure(call: Call<ArrayList<Moment>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}