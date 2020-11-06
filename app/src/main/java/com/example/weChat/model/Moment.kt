package com.example.weChat.model


class Moment(
    var content: String?,
    var images: Array<String>?,
    var sender: Sender?,
    var comments: Array<Comment>?,
    var error: String?,
    val unknownError: String?
) {

    companion object {
        fun initData(num: Int): ArrayList<Moment> {
            val moments = ArrayList<Moment>()
            val images: Array<String> =
                arrayOf(
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604591503155&di=8fe9ec074a535324dae74d401421e9fc&imgtype=0&src=http%3A%2F%2Fimg.duoziwang.com%2F2020%2F01%2F03212017713790.jpg",
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604591503155&di=8fe9ec074a535324dae74d401421e9fc&imgtype=0&src=http%3A%2F%2Fimg.duoziwang.com%2F2020%2F01%2F03212017713790.jpg"
                )

            val sender: Sender = Sender(
                "肖美琦",
                "Maggie",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1604591503155&di=681756f9c10422cc09d7c0ec807c2572&imgtype=0&src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202007%2F28%2F20200728230717_evHvC.thumb.400_0.jpeg"
            )
            val comment = Comment("nice!", sender)
            val comments: Array<Comment> = arrayOf(comment, comment)
            val moment = Moment("内容是今日落大雨", images, sender, comments, null,null)
            for (i in 1..num) {
                moments.add(moment)
            }
            return moments
        }
    }
}