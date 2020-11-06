package com.example.weChat.viewModel

import com.example.weChat.model.Moment
import com.example.weChat.model.Profile
import retrofit2.Call

interface WeChatRepository {

    fun getProfileData(): Call<Profile>

    fun getMomentsData(): Call<ArrayList<Moment>>
}