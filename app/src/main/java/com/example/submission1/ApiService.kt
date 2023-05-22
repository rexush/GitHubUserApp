package com.example.submission1

import com.example.submission1.models.UserDetailResponse
import com.example.submission1.models.UserFollowersResponse
import com.example.submission1.models.UserFollowingResponse
import com.example.submission1.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getSearchUser(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users")
    fun getUser(): Call<UserResponse>

    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<UserFollowersResponse>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<UserFollowingResponse>
}