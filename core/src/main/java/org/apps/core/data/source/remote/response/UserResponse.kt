package org.apps.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse (

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("html_url")
    val htmlUrl: String,
)