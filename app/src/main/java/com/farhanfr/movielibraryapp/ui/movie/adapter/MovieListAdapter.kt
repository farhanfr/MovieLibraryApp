package com.farhanfr.movielibraryapp.ui.movie.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.model.MovieModel
import com.farhanfr.movielibraryapp.ui.movie.fragment.DetailMovieFragment
import com.squareup.picasso.Picasso

class MovieListAdapter(val context: Context): RecyclerView.Adapter<MovieListAdapter.HolderData>() {

    var movieModel:ArrayList<MovieModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.HolderData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_movie_list,parent,false)
        return HolderData(view)
    }

    override fun getItemCount(): Int {
        return movieModel.size
    }

    fun setMovieData(movieModel:ArrayList<MovieModel>){
        this.movieModel= movieModel
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieListAdapter.HolderData, position: Int) {
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185/"+movieModel.get(position).poster_path).into(holder.ivMoviePhoto)
        holder.tvTitleMovie.text = movieModel.get(position).title
        holder.tvDescription.text = movieModel.get(position).overview
        holder.tvRatingMovie.text = movieModel.get(position).vote_average.toString()
        holder.cvMovie.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("movie_id",movieModel.get(position).id)
            bundle.putString("titleMovie",movieModel.get(position).title)
            bundle.putString("descMovie",movieModel.get(position).overview)
            bundle.putString("imgMovie",movieModel.get(position).poster_path)
            bundle.putString("avgRating",movieModel.get(position).vote_average.toString())
            bundle.putDouble("popularity",movieModel.get(position).popularity)
            bundle.putString("original_language",movieModel.get(position).original_language)
            val toDetailMov = DetailMovieFragment()
            toDetailMov.arguments = bundle
            replaceFragement(toDetailMov)
        }
    }

    fun replaceFragement(fragment: Fragment){
        val tr = (context as AppCompatActivity).supportFragmentManager.beginTransaction()
        tr.replace(R.id.flContainer,fragment)
        tr.addToBackStack(null)
        tr.commit()
    }

    class HolderData(val view: View):RecyclerView.ViewHolder(view){
        val ivMoviePhoto:ImageView = view.findViewById(R.id.ivMoviePhoto)
        val tvTitleMovie:TextView = view.findViewById(R.id.tvTitleMovie)
        val tvDescription:TextView = view.findViewById(R.id.tvDescriptionMovie)
        val tvRatingMovie:TextView = view.findViewById(R.id.tvRatingMovie)
        val cvMovie:CardView = view.findViewById(R.id.cvMovie)
    }
}