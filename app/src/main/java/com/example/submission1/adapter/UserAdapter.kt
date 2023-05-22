package com.example.submission1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.submission1.databinding.ItemUsersBinding
import com.example.submission1.models.UserResponse


class UserAdapter(private val list : ArrayList<UserResponse.Item>) : RecyclerView.Adapter<UserAdapter.ListViewHolder>() {

   private var onItemClickCallback: OnItemClickCallback?= null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback= onItemClickCallback
    }

    fun setList(users : ArrayList<UserResponse.Item>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
    inner class ListViewHolder(val binding : ItemUsersBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserResponse.Item){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }


            binding.apply {
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .into(imgUser)
                tvUser.text = user.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemUsersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data : UserResponse.Item)
    }
}