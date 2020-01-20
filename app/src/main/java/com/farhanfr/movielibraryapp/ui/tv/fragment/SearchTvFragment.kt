package com.farhanfr.movielibraryapp.ui.tv.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.model.TvModel
import com.farhanfr.movielibraryapp.ui.tv.adapter.TvListAdapter
import com.farhanfr.movielibraryapp.ui.tv.viewmodel.TvViewModel
import kotlinx.android.synthetic.main.fragment_search_tv.*

/**
 * A simple [Fragment] subclass.
 */
class SearchTvFragment : Fragment() {

    private lateinit var viewModel: TvViewModel
    private lateinit var tvListAdapter: TvListAdapter

    companion object{
        fun getInstance() = SearchTvFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tvListAdapter = TvListAdapter(activity!!)
        rcTvShow.layoutManager=LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rcTvShow.adapter=tvListAdapter

        val bundle = arguments
        val getQuerySearch = bundle?.getString("querySearch")

        viewModel = ViewModelProviders.of(this).get(TvViewModel::class.java)
        viewModel.searchTvData(getQuerySearch)
        viewModel.getTvList().observe(this, Observer { TvModel ->
            tvListAdapter.setTvListAdapter(TvModel)
        })
    }

}
