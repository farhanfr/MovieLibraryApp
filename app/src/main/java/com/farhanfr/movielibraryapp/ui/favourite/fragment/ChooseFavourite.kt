package com.farhanfr.movielibraryapp.ui.favourite.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager

import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.ui.favourite.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.fragment_choose_favourite.*

/**
 * A simple [Fragment] subclass.
 */
class ChooseFavourite : Fragment() {

    companion object {
        fun newInstance() = ChooseFavourite()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_choose_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vpFavourite.adapter = MyPagerAdapter(childFragmentManager)
        tlMain.setupWithViewPager(vpFavourite)
    }


}
