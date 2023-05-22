package com.example.submission1.models

import com.google.gson.annotations.SerializedName

class UserFollowersResponse : ArrayList<UserFollowersResponse.UserFollowerResponseItem>(){
    data class UserFollowerResponseItem(
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("login")
        val login: String
    )
}