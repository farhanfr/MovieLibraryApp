package com.farhanfr.movielibraryapp.room.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.farhanfr.movielibraryapp.room.repository.MovieRepository
import com.farhanfr.movielibraryapp.room.table.TableMovie

class MovieRoomViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MovieRepository(application)
    private val dataMovie:LiveData<MutableList<TableMovie>> = repository.getMovie()

    fun addMovieVM(tableMovie: TableMovie){
        repository.addMovie(tableMovie)
    }

    fun getMovieVM():LiveData<MutableList<TableMovie>>{
        return dataMovie
    }

    fun deleteMovieVM(tableMovie: TableMovie){
        repository.deleteMovie(tableMovie)
    }

}