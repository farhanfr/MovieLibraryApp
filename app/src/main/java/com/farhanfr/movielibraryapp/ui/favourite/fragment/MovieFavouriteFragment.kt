package com.farhanfr.movielibraryapp.ui.favourite.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.room.table.TableMovie
import com.farhanfr.movielibraryapp.room.viewmodel.MovieRoomViewModel
import com.farhanfr.movielibraryapp.ui.favourite.adapter.MovieFavouriteAdapter
import kotlinx.android.synthetic.main.fragment_movie_favourite.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFavouriteFragment : Fragment() {

    lateinit var movieRoomViewModel: MovieRoomViewModel
    lateinit var movieFavouriteAdapter: MovieFavouriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieFavouriteAdapter = MovieFavouriteAdapter(activity!!)
        rcFavMov.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        rcFavMov.adapter = movieFavouriteAdapter
        movieRoomViewModel = ViewModelProviders.of(this).get(MovieRoomViewModel::class.java)
        movieRoomViewModel.getMovieVM().observe(this, Observer { TableMovie ->
            movieFavouriteAdapter.setFavouriteMovie(TableMovie)
        })

        val swipeDelete = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                movieRoomViewModel.deleteMovieVM(movieFavouriteAdapter.getMovieAt(viewHolder.adapterPosition))
                Toast.makeText(activity, "Favourite Movie deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(rcFavMov)
    }


}
