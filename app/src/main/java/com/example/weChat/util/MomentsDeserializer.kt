package com.example.weChat.util

import com.example.weChat.model.Comment
import com.example.weChat.model.Moment
import com.example.weChat.model.Profile
import com.example.weChat.model.Sender
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
//
//class MomentsDeserializer : JsonDeserializer<Moment> {
//    @Throws(JsonParseException::class)
//    override fun deserialize(
//        json: JsonElement,
//        typeOfT: Type,
//        context: JsonDeserializationContext
//    ): Moment {
//        val jObject = json.asJsonObject
//        val content = jObject.get("content").asString
//        val images = jObject.get("images").asJsonArray
//        val sender = jObject.get("sender")
//        val comments = jObject.get("comments").asJsonArray
//        val error = jObject.get("error").asString
//        val unknownError = jObject.get("unknown error").asString
//
//        return Moment(content,images,sender,comments,error,unknownError)
//    }
//}




//class Moment(
//    var content: String?,
//    var images: Array<String>?,
//    var sender: Sender?,
//    var comments: Array<Comment>?,
//    var error: String?,
//) {