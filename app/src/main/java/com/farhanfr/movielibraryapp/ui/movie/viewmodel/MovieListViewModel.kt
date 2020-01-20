package com.farhanfr.movielibraryapp.ui.movie.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.model.MovieModel
import com.farhanfr.movielibraryapp.retrofit.network.NetworkConfig
import com.farhanfr.movielibraryapp.retrofit.response.ResponseMovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    val movieModel = MutableLiveData<ArrayList<MovieModel>>()

    fun getMovieList():MutableLiveData<ArrayList<MovieModel>>{
        return movieModel
    }

    fun setMovieList(){
        NetworkConfig().api().getMovies().enqueue(object : Callback<ResponseMovieModel> {
            override fun onFailure(call: Call<ResponseMovieModel>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(getApplication(),R.string.responseFailure,Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ResponseMovieModel>, response: Response<ResponseMovieModel>) {
                if (response.isSuccessful){
                    movieModel.value = response.body()?.results
                }
                else{
                    Toast.makeText(getApplication(),R.string.responseMovieFailure,Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    fun searchMovies(searchQuery: String?) {
        searchQuery?.let {
            NetworkConfig().api().searchMovie(it).enqueue(object : Callback<ResponseMovieModel> {
                override fun onFailure(call: Call<ResponseMovieModel>, t: Throwable) {
                    Toast.makeText(getApplication(),R.string.responseFailure,Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ResponseMovieModel>, response: Response<ResponseMovieModel>) {
                    if (response.isSuccessful){
                        movieModel.value = response.body()?.results
                        Toast.makeText(getApplication(),R.string.responseMovieSearchSuccess,Toast.LENGTH_SHORT).show()
                    }
                    else{
                        Toast.makeText(getApplication(),R.string.responseMovieFailure,Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }




}
