package com.example.submission1.follow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1.ApiConfig
import com.example.submission1.models.UserFollowersResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {
    val listFollower = MutableLiveData<ArrayList<UserFollowersResponse.UserFollowerResponseItem>>()

    fun setListFollower(username: String){
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<UserFollowersResponse> {
            override fun onResponse(call: Call<UserFollowersResponse>, response: Response<UserFollowersResponse>) {
                if (response.isSuccessful){
                    listFollower.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserFollowersResponse>, t: Throwable) {
                t.message?.let { Log.d("onFailure : ", it) }
            }

        })
    }

    fun getListFollower(): LiveData<ArrayList<UserFollowersResponse.UserFollowerResponseItem>> {
        return listFollower
    }
}