package com.example.weChat.service

import com.example.weChat.model.Profile
import retrofit2.Call
import retrofit2.http.GET

interface ProfileService {

    @GET("/fake-data/data/weixin/profile.json")
    fun getProfileData() : Call<Profile>

}