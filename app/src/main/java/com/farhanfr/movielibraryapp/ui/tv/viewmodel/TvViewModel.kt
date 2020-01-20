package com.farhanfr.movielibraryapp.ui.tv.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.model.TvModel
import com.farhanfr.movielibraryapp.retrofit.network.NetworkConfig
import com.farhanfr.movielibraryapp.retrofit.response.ResponseTvModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvViewModel(application: Application) : AndroidViewModel(application) {

    val tvModel= MutableLiveData<ArrayList<TvModel>>()

    fun getTvList():MutableLiveData<ArrayList<TvModel>>{
        return tvModel
    }

    fun setTvList(){
        NetworkConfig().api().getTv().enqueue(object : Callback<ResponseTvModel> {
            override fun onFailure(call: Call<ResponseTvModel>, t: Throwable) {
                Toast.makeText(getApplication(), R.string.responseFailure,Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseTvModel>, response: Response<ResponseTvModel>) {
                if (response.isSuccessful){
                   tvModel.value = response.body()?.results
                }
                else{
                    Toast.makeText(getApplication(), R.string.responseMovieFailure,Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    fun searchTvData(querySearch: String?) {
        querySearch?.let {
            NetworkConfig().api().searchTv(it).enqueue(object : Callback<ResponseTvModel> {
                override fun onFailure(call: Call<ResponseTvModel>, t: Throwable) {
                    Toast.makeText(getApplication(), R.string.responseFailure,Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ResponseTvModel>, response: Response<ResponseTvModel>
                ) {
                    if (response.isSuccessful){
                        tvModel.value = response.body()?.results
                    }
                    else{
                        Toast.makeText(getApplication(), R.string.responseMovieSearchSuccess,Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

}
