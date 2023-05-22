package com.example.submission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1.databinding.ItemUsersBinding
import com.example.submission1.models.UserFollowersResponse

class FollowersAdapter : RecyclerView.Adapter<FollowersAdapter.FollowerViewHolder>() {
    private val listFollower = ArrayList<UserFollowersResponse.UserFollowerResponseItem>()

    fun setList(followers: ArrayList<UserFollowersResponse.UserFollowerResponseItem>){
        listFollower.clear()
        listFollower.addAll(followers)
        notifyDataSetChanged()
    }

     class FollowerViewHolder(val binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: UserFollowersResponse.UserFollowerResponseItem){
            binding.apply {
                Glide.with(itemView)
                    .load(follower.avatarUrl)
                    .into(imgUser)
                tvUser.text = follower.login
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val view = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowerViewHolder(view)
    }

    override fun getItemCount(): Int = listFollower.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(listFollower[position])
    }

}