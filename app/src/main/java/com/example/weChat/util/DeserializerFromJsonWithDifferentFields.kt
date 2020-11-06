package com.example.weChat.util

import com.example.weChat.model.Profile
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class DeserializerFromJsonWithDifferentFields : JsonDeserializer<Profile> {
    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Profile {
        val jObject = json.asJsonObject
        val image = jObject.get("profile-image").asString
        val avatar = jObject.get("avatar").asString
        val nick = jObject.get("nick").asString
        val username = jObject.get("username").asString
        return Profile(image, avatar, nick, username)
    }
}