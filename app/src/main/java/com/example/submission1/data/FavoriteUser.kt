package com.example.submission1.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Favorite_User")
data class FavoriteUser(
    val login: String,
    @PrimaryKey
    val id: Int,
    val avatar_url: String
): Serializable
