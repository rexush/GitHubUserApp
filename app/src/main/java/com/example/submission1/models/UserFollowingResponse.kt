package com.example.submission1.models

import com.google.gson.annotations.SerializedName

class UserFollowingResponse : ArrayList<UserFollowingResponse.UserFollowingResponseItem>(){
    data class UserFollowingResponseItem(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("login")
        val login: String
    )
}