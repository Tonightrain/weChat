package com.example.weChat.service

import com.example.weChat.model.Moment
import retrofit2.Call
import retrofit2.http.GET

interface MomentsService {

    @GET("/fake-data/data/weixin/tweets.json")
    fun getMomentsData(): Call<ArrayList<Moment>>
}