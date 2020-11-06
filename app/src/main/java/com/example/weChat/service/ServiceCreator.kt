package com.example.weChat.service

import com.example.weChat.model.Profile
import com.example.weChat.util.DeserializerFromJsonWithDifferentFields
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {

    private const val BASE_URL = "https://twc-android-bootcamp.github.io"

    private val retrofitForProfile =
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().registerTypeAdapter(
                        Profile::class.java,
                        DeserializerFromJsonWithDifferentFields()
                    ).create()
                )
            )
            .build()

    fun <T> create(serviceClass: Class<T>): T = retrofitForProfile.create(serviceClass)

}