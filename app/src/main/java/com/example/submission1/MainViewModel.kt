package com.example.submission1

import android.util.Log
import androidx.lifecycle.*
import com.example.submission1.models.UserResponse
import com.example.submission1.setting.SettingPreferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: SettingPreferences): ViewModel() {
    private val _listUser = MutableLiveData<ArrayList<UserResponse.Item>>()
    val listUser : LiveData<ArrayList<UserResponse.Item>> = _listUser

    private val _snackbar = MutableLiveData<Event<String>>()
    val snackbar : LiveData<Event<String>> = _snackbar

    companion object{
        private const val TAG = "MainViewModel"
    }

    fun setSearchUser(query: String){
        val client = ApiConfig.getApiService().getSearchUser(query)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _listUser.postValue(response.body()?.items)
                    }
                } else{
                    _snackbar.value = Event(response.message().toString())
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })

    }

    fun getUser(){
        val client = ApiConfig.getApiService().getUser()
        client.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if(responseBody !=null){
                        _listUser.value = response.body()?.items
                    }
                }else{
                    Log.e(TAG, "onFailure : ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure : ${t.message}")
            }
        })
    }

    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

}