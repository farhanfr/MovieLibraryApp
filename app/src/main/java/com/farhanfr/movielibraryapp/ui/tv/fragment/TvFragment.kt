package com.farhanfr.movielibraryapp.ui.tv.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.model.TvModel
import com.farhanfr.movielibraryapp.ui.tv.adapter.TvListAdapter
import com.farhanfr.movielibraryapp.ui.tv.viewmodel.TvViewModel
import kotlinx.android.synthetic.main.tv_fragment.*

class TvFragment : Fragment() {

    companion object {
        fun newInstance() = TvFragment()
    }

    private lateinit var viewModel: TvViewModel
    private lateinit var tvListAdapter: TvListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tv_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        svTv.queryHint = "Search Your Tv Show"
        svTv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                var bundle = Bundle()
                bundle.putString("querySearch",query)
                var toTvSearch = SearchTvFragment()
                toTvSearch.arguments = bundle
                replaceFragment(toTvSearch)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        tvListAdapter = TvListAdapter(activity!!)
        rcTvShow.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rcTvShow.adapter = tvListAdapter

        viewModel = ViewModelProviders.of(this).get(TvViewModel::class.java)
        viewModel.setTvList()
        viewModel.getTvList().observe(this, Observer { TvModel ->
            tvListAdapter.setTvListAdapter(TvModel)
        })
    }

    fun replaceFragment(fragment: Fragment){
        val tr = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        tr.replace(R.id.flContainer,fragment)
        tr.commit()

    }

}
