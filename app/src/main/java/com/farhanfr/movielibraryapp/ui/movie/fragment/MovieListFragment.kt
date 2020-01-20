package com.farhanfr.movielibraryapp.ui.movie.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.api.ApiService
import com.farhanfr.movielibraryapp.retrofit.model.MovieModel
import com.farhanfr.movielibraryapp.retrofit.network.NetworkConfig
import com.farhanfr.movielibraryapp.retrofit.response.ResponseMovieModel
import com.farhanfr.movielibraryapp.ui.movie.adapter.MovieListAdapter
import com.farhanfr.movielibraryapp.ui.movie.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.movie_list_fragment.*
import kotlinx.android.synthetic.main.top_nav.*
import retrofit2.Call
import androidx.core.content.ContextCompat
import androidx.core.os.HandlerCompat.postDelayed

import android.os.Handler
import kotlinx.android.synthetic.main.movie_list_fragment.view.*


class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_list_fragment, container, false)
//        view.refresh.setColorSchemeResources(R.color.colorBlue,R.color.colorAccent)
//        view.refresh.setOnRefreshListener {
//            object : SwipeRefreshLayout.OnRefreshListener {
//                override fun onRefresh() {
//                    Handler().postDelayed(Runnable {
//                        view.refresh.isRefreshing=false
//                    }, 3000)
//                }
//
//            } }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        movieListAdapter = MovieListAdapter(activity!!)
        rcMovieList.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rcMovieList.adapter = movieListAdapter


        svMovie.queryHint = "Search Your Movie"
        svMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val bundle = Bundle()
                bundle.putString("querySearch",query)
                var toSearchMovie = SearchMovieFragment()
                toSearchMovie.arguments = bundle
                replaceFragement(toSearchMovie)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        viewModel.setMovieList()
        viewModel.getMovieList().observe(this, Observer { MovieModel ->
            movieListAdapter.setMovieData(MovieModel)
        })
    }

    fun replaceFragement(fragment: Fragment){
        var tr = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        tr.replace(R.id.flContainer,fragment)
        tr.commit()
    }

}
