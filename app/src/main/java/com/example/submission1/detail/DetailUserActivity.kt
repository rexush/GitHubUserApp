package com.example.submission1.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.submission1.R
import com.example.submission1.adapter.SectionPagerAdapter
import com.example.submission1.databinding.ActivityDetailUserBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel by viewModels<DetailUserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {

        val actionBar = supportActionBar
        actionBar?.title = "Detail GitHub User"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val id = intent.getIntExtra(EXTRA_ID,0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        if (username != null) {
            detailViewModel.setUserDetail(username)
        }
        detailViewModel.isLoading.observe(this) {
            showLoading(it)
        }
        detailViewModel.user.observe(this) {
            if (it != null) {
                binding.apply {
                    tvName.text = it.name
                    tvUsername.text = it.login
                    tvBio.text = it.bio
                    tvFollowers.text = resources.getString(R.string.followers_user, it.followers)
                    tvFollowing.text = resources.getString(R.string.following_user, it.following)
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .into(imgUser)
                }
            }
        }

        var _isChecked = false
        CoroutineScope(Dispatchers.IO).launch {
            val count = detailViewModel.checkUser(id)
            withContext(Dispatchers.Main){
                if (count != null){
                    if (count>0){
                        binding.buttonFav.isChecked = true
                        _isChecked = true
                    } else {
                        binding.buttonFav.isChecked = false
                        _isChecked = false
                    }
                }
            }
        }

        binding.buttonFav.setOnClickListener{
            _isChecked = !_isChecked
            if (_isChecked){
                detailViewModel.addToFavorite(username!!, id, avatarUrl!!)
            }else{
                detailViewModel.removeFromFavorite(id)
            }
            binding.buttonFav.isChecked = _isChecked
        }

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            vpFol.adapter = sectionPagerAdapter
            tabFol.setupWithViewPager(vpFol)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbFollow2.visibility = View.VISIBLE
        } else {
            binding.pbFollow2.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }
}