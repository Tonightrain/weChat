package com.example.weChat.model


class Moment(
    var content: String?,
    var images: ArrayList<String>?,
    var sender: Sender?,
    var comments: ArrayList<Comment>?,
    var error: String?,
    val unknownError: String?
)