package com.example.submission1.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.adapter.UserAdapter
import com.example.submission1.data.FavoriteUser
import com.example.submission1.databinding.ActivityFavoriteBinding
import com.example.submission1.detail.DetailUserActivity
import com.example.submission1.models.UserResponse

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        val actionBar = supportActionBar
        actionBar?.title = "Favorite User"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = ArrayList<UserResponse.Item>()
        adapter = UserAdapter(list)
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: UserResponse.Item) {
                Intent(this@FavoriteActivity, DetailUserActivity::class.java).also{
                    it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailUserActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailUserActivity.EXTRA_URL, data.avatarUrl)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            rvFav.setHasFixedSize(true)
            rvFav.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvFav.adapter = adapter
        }

        viewModel.getFavoriteUser()?.observe(this) {
            if (it != null) {
                val list = mapList(it)
                adapter.setList(list)
            }
        }
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<UserResponse.Item> {
        val listUser = ArrayList<UserResponse.Item>()
        for (user in users){
            val userMapped = UserResponse.Item(user.login, user.id, user.avatar_url)
            listUser.add(userMapped)
        }
        return listUser
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}