package com.example.weChat.model

import com.example.weChat.service.MomentsService
import com.example.weChat.service.ProfileService
import com.example.weChat.service.ServiceCreator
import com.example.weChat.viewModel.WeChatRepository
import io.reactivex.rxjava3.core.Maybe
import retrofit2.Call

class WeChatDBDataSource : WeChatRepository {

    private  val profileService: ProfileService = ServiceCreator.create(ProfileService::class.java)
    private val momentsService: MomentsService = ServiceCreator.create(MomentsService::class.java)


    override fun getProfileData(): Call<Profile> {
//        profileService = ServiceCreator.create(ProfileService::class.java)
       return profileService.getProfileData()
    }

    override fun getMomentsData(): Call<ArrayList<Moment>> {
//        momentsService = ServiceCreator.create(MomentsService::class.java)
       return momentsService.getMomentsData()
    }


}