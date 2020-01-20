package com.farhanfr.movielibraryapp.retrofit.network

import com.farhanfr.movielibraryapp.retrofit.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConfig {

    companion object{
        val BASE_URL = "https://api.themoviedb.org/3/"
        val okHttp = OkHttpClient.Builder()
    }

    fun getNetwork(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttp.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun api():ApiService{
        return getNetwork().create(ApiService::class.java)
    }
}