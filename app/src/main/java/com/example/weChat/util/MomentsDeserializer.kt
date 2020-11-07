package com.example.weChat.util

import com.example.weChat.model.Comment
import com.example.weChat.model.Moment
import com.example.weChat.model.Sender
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class MomentsDeserializer : JsonDeserializer<Moment> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Moment {
        val jObject = json.asJsonObject
        val content = jObject.get("content")?.asString
        val images = jObject.get("images")?.asJsonArray
        val sender = jObject.get("sender")?.asJsonObject
        val comments = jObject.get("comments")?.asJsonArray
        val error = jObject.get("error")?.asString
        val unknownError = jObject.get("unknown error")?.asString

        val imagesAfterConvert = ArrayList<String>()
        images?.forEach {
            imagesAfterConvert.add(it.asString)
        }

        val commentsAfterConvert = ArrayList<Comment>()
        comments?.forEach {
            val commentObject = it.asJsonObject
            val content = commentObject.get("content").asString
            val sender = commentObject.get("sender").asJsonObject

            val username = sender.get("username").asString
            val nick = sender.get("nick").asString
            val avatar = sender.get("avatar").asString

            commentsAfterConvert.add(Comment(content, Sender(username, nick, avatar)))
        }


        val username = sender?.get("username")?.asString
        val nick = sender?.get("nick")?.asString
        val avatar = sender?.get("avatar")?.asString
        val senderConvert = Sender(username, nick, avatar)


        return Moment(
            content,
            imagesAfterConvert,
            senderConvert,
            commentsAfterConvert,
            error,
            unknownError
        )
    }
}


