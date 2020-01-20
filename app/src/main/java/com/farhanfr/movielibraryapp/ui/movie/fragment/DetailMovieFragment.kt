package com.farhanfr.movielibraryapp.ui.movie.fragment


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProviders

import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.room.table.TableMovie
import com.farhanfr.movielibraryapp.room.viewmodel.MovieRoomViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.dialog_detail_movie_layout.*
import kotlinx.android.synthetic.main.dialog_detail_movie_layout.view.*
import kotlinx.android.synthetic.main.fragment_detail_movie.*


/**
 * A simple [Fragment] subclass.
 */
class DetailMovieFragment : Fragment() {

    lateinit var viewModel: MovieRoomViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bundle = arguments
        //Get data
        val getMovieId = bundle?.getInt("movie_id")
        val getPoster = bundle?.getString("imgMovie")
        val getTitle = bundle?.getString("titleMovie")
        val getAvgRating = bundle?.getString("avgRating")
        val getPopularity = bundle?.getDouble("popularity")
        val getDescMovie = bundle?.getString("descMovie")
        val getOriginalLanguage = bundle?.getString("original_language")

        //Implement get data
        Picasso.with(context).load("https://image.tmdb.org/t/p/original/"+getPoster).into(ivDetailMov)

        val dialog = BottomSheetDialog(activity!!)
        val viewDialog = layoutInflater.inflate(R.layout.dialog_detail_movie_layout,null,false)

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185/"+getPoster).into(viewDialog.ivPhotoMov)
        viewDialog.tvTitleDetailMov.text = getTitle
        viewDialog.tvOverview.text = getDescMovie
        viewDialog.tvRatingDetailMov.text = getAvgRating
        viewDialog.tvLangMov.text = getOriginalLanguage

        //Action Detail Movie
        viewDialog.ivRating.setOnClickListener {
            Toast.makeText(activity,"Rating Movie Info",Toast.LENGTH_SHORT).show()
        }
        viewDialog.ivLang.setOnClickListener {
            Toast.makeText(activity,"Languange Movie Info",Toast.LENGTH_SHORT).show()
        }
        viewDialog.ivPopular.setOnClickListener {
            Toast.makeText(activity,"Popularity Movie Info",Toast.LENGTH_SHORT).show()
        }

        //SEK RONG MARI
        if (getPopularity!! > 100.000){
            viewDialog.tvPopularMov.text = "100K"
        }

        //Automatic Show
        dialog.setContentView(viewDialog)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()

        //Action BottomSheet Dialog
        viewDialog.ivCloseDet.setOnClickListener {
            dialog.dismiss()
        }
        fabDetailMov.setOnClickListener {
            dialog.show()
        }

        //Add Favourite Movie
        fabFavouriteMov.setOnClickListener {
            viewModel = ViewModelProviders.of(this).get(MovieRoomViewModel::class.java)
            viewModel.addMovieVM(TableMovie(null,getMovieId,getPoster,getTitle,getDescMovie,getAvgRating))
            Toast.makeText(context,"add to movie favourite",Toast.LENGTH_SHORT).show()
        }

    }


}

