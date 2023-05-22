package com.example.submission1.follow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1.adapter.FollowersAdapter
import com.example.submission1.databinding.FragmentFollowBinding
import com.example.submission1.detail.DetailUserActivity

class FragmentFollowers : Fragment() {

    private var _binding : FragmentFollowBinding? = null
    private val binding get() = _binding!!
    private lateinit var followersViewModel: FollowersViewModel
    private lateinit var adapter: FollowersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        adapter = FollowersAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollow.setHasFixedSize(true)
            rvFollow.layoutManager = LinearLayoutManager(activity)
            rvFollow.adapter = adapter
        }
        showLoading(true)
        followersViewModel =ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        followersViewModel.setListFollower(username)
        followersViewModel.getListFollower().observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setList(it)
                showLoading(false)
            }
        }
    }

    private fun showLoading(state: Boolean) { binding.pbFollow.visibility = if (state) View.VISIBLE else View.GONE }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}