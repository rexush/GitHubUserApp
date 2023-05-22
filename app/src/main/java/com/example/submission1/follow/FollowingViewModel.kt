package com.example.submission1.follow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.submission1.ApiConfig
import com.example.submission1.models.UserFollowingResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {
    val listFollowing = MutableLiveData<ArrayList<UserFollowingResponse.UserFollowingResponseItem>>()

    fun setListFollowing(username: String){
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<UserFollowingResponse> {
            override fun onResponse(call: Call<UserFollowingResponse>, response: Response<UserFollowingResponse>) {
                if (response.isSuccessful){
                    listFollowing.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<UserFollowingResponse>, t: Throwable) {
                t.message?.let { Log.d("onFailure : ", it) }
            }

        })
    }

    fun getListFollowing(): LiveData<ArrayList<UserFollowingResponse.UserFollowingResponseItem>> {
        return listFollowing
    }
}