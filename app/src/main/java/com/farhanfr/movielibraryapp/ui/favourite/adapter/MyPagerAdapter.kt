package com.farhanfr.movielibraryapp.ui.favourite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.farhanfr.movielibraryapp.ui.favourite.fragment.MovieFavouriteFragment
import com.farhanfr.movielibraryapp.ui.favourite.fragment.TvFavouriteFragment

class MyPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val pageList = listOf(MovieFavouriteFragment(),TvFavouriteFragment())

    override fun getItem(position: Int): Fragment {
        return pageList[position]
    }

    override fun getCount(): Int {
        return pageList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Movie Favourite"
            1 -> "Tv Favourite"
            else -> "Third Tab"
        }
    }


}