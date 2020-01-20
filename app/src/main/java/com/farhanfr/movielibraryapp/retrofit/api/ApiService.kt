package com.farhanfr.movielibraryapp.retrofit.api

import com.farhanfr.movielibraryapp.BuildConfig
import com.farhanfr.movielibraryapp.retrofit.model.TvModel
import com.farhanfr.movielibraryapp.retrofit.response.ResponseMovieModel
import com.farhanfr.movielibraryapp.retrofit.response.ResponseTvModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    //Get Movie
    @GET("discover/movie?api_key=9ec406e751cc6d66bf36e11ffab6791b&language=en-US")
    fun getMovies():Call<ResponseMovieModel>

    //Search
    @GET("search/movie?api_key=9ec406e751cc6d66bf36e11ffab6791b&language=en-US&page=1&include_adult=false")
    fun searchMovie(
        @Query("query") title:String
    ):Call<ResponseMovieModel>

    //Get Tv
    @GET("discover/tv?api_key=9ec406e751cc6d66bf36e11ffab6791b&language=en-US")
    fun getTv(): Call<ResponseTvModel>

    //Search
    @GET("search/tv?api_key=9ec406e751cc6d66bf36e11ffab6791b&language=en-US&page=1&include_adult=false")
    fun searchTv(
        @Query("query") original_name: String
    ): Call<ResponseTvModel>

}