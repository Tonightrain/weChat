package com.example.weChat.util

import android.app.Application
import android.content.Context
import com.example.weChat.model.WeChatDBDataSource
import com.example.weChat.viewModel.WeChatRepository

class MyApplication : Application() {
    companion object{
        lateinit var context: Context
        lateinit var weChatDBDataSource: WeChatRepository
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        weChatDBDataSource = WeChatDBDataSource()
    }
}