package com.example.submission1.models

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: ArrayList<Item>,
    @SerializedName("total_count")
    val totalCount: Int
) {
    data class Item(
        @SerializedName("login")
        val login: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("avatar_url")
        val avatarUrl: String
    )
}