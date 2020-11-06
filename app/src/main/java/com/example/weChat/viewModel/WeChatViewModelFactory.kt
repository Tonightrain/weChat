package com.example.weChat.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class WeChatViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WeChatViewModel() as T
    }

}