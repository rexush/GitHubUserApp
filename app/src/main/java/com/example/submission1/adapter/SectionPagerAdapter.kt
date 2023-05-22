package com.example.submission1.adapter

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.submission1.R
import com.example.submission1.follow.FragmentFollowers
import com.example.submission1.follow.FragmentFollowing

class SectionPagerAdapter(private val ctx : Context, fragmana: FragmentManager, data : Bundle) : FragmentPagerAdapter(fragmana, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var fragBund : Bundle

    init{
        fragBund = data
    }
    @StringRes
    private  val TAB_TITLE = intArrayOf(R.string.followers, R.string.following)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = FragmentFollowers()
            1 -> fragment = FragmentFollowing()
        }
        fragment?.arguments = this.fragBund
        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ctx.resources.getString(TAB_TITLE[position])
    }

}