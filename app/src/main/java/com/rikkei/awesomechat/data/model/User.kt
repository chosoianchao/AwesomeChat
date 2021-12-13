package com.rikkei.awesomechat.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var uid: String? = null,
    val email: String,
    val password: String,
    var name: String = "",
    var dob: String = "",
    var avatar: String = "",
) : Parcelable {

}
