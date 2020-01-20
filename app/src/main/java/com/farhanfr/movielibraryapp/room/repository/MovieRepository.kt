package com.farhanfr.movielibraryapp.room.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.farhanfr.movielibraryapp.room.dao.MovieDao
import com.farhanfr.movielibraryapp.room.db.DbRoom
import com.farhanfr.movielibraryapp.room.table.TableMovie

class MovieRepository(application: Application) {

    private var movieDao:MovieDao
    private var dataMovie: LiveData<MutableList<TableMovie>>

    init {
        val db:DbRoom = DbRoom.getInstance(
            application.applicationContext
        )
        movieDao = db.movieDao()
        dataMovie =movieDao.getMovieFavourite()
    }

    fun addMovie(tableMov: TableMovie){
        addMovieAsync(movieDao).execute(tableMov)
    }

    fun deleteMovie(tableMov: TableMovie){
        deleteMovieAsync(movieDao).execute(tableMov)
    }

    fun getMovie():LiveData<MutableList<TableMovie>>{
        return dataMovie
    }

    private class addMovieAsync(val movieDao: MovieDao): AsyncTask<TableMovie, Unit, Unit>() {
        override fun doInBackground(vararg tableMovie: TableMovie?) {
            movieDao.addFavouriteMovie(tableMovie[0]!!)
        }
    }

    private class deleteMovieAsync(val movieDao: MovieDao): AsyncTask<TableMovie, Unit, Unit>() {
        override fun doInBackground(vararg tableMovie: TableMovie?) {
            movieDao.deleteFavouriteMovie(tableMovie[0]!!)
        }
    }


}