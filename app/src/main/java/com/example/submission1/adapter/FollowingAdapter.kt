package com.example.submission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1.databinding.ItemUsersBinding
import com.example.submission1.models.UserFollowingResponse

class FollowingAdapter : RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {
    private val listFollowing = ArrayList<UserFollowingResponse.UserFollowingResponseItem>()

    fun setList(following: ArrayList<UserFollowingResponse.UserFollowingResponseItem>){
        listFollowing.clear()
        listFollowing.addAll(following)
        notifyDataSetChanged()
    }

    class FollowingViewHolder(val binding: ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(follower: UserFollowingResponse.UserFollowingResponseItem){
            binding.apply {
                Glide.with(itemView)
                    .load(follower.avatarUrl)
                    .into(imgUser)
                tvUser.text = follower.login
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingViewHolder {
        val view = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowingViewHolder(view)
    }

    override fun getItemCount(): Int = listFollowing.size

    override fun onBindViewHolder(holder: FollowingViewHolder, position: Int) {
        holder.bind(listFollowing[position])
    }

}