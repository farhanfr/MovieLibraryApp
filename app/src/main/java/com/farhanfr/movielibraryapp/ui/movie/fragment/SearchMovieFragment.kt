package com.farhanfr.movielibraryapp.ui.movie.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.model.MovieModel
import com.farhanfr.movielibraryapp.ui.movie.adapter.MovieListAdapter
import com.farhanfr.movielibraryapp.ui.movie.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_search_movie.*
import kotlinx.android.synthetic.main.search_top_nav.*

/**
 * A simple [Fragment] subclass.
 */
class SearchMovieFragment : Fragment() {

    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    companion object {
        fun newInstance() = SearchMovieFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieListAdapter = MovieListAdapter(activity!!)
        rcMovieList.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rcMovieList.adapter = movieListAdapter
        val bundle = arguments
        val getSearchQuery = bundle!!.getString("querySearch")
        Log.d("TESTTTT",getSearchQuery)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        viewModel.searchMovies(getSearchQuery)
        viewModel.getMovieList().observe(this, Observer { MovieModel ->
            movieListAdapter.setMovieData(MovieModel)
        })
    }


}
