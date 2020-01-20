package com.farhanfr.movielibraryapp.ui.favourite.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.room.table.TableMovie
import com.squareup.picasso.Picasso

class MovieFavouriteAdapter(val context: Context): RecyclerView.Adapter<MovieFavouriteAdapter.HolderData>() {

    private var tableMovie:MutableList<TableMovie> = ArrayList()
    private var listener:OnClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieFavouriteAdapter.HolderData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_fav_mov,parent,false)
        return HolderData(view)
    }

    override fun getItemCount(): Int {
        return tableMovie.size
    }

    override fun onBindViewHolder(holder: MovieFavouriteAdapter.HolderData, position: Int) {
        holder.tvTitleFavMov.text = tableMovie.get(position).title
        holder.tvDescFavMov.text = tableMovie.get(position).overview
        holder.tvRatingFavMov.text = tableMovie.get(position).vote_average.toString()
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185/"+tableMovie.get(position).poster_path).into(holder.ivPhotoFavMov)
        holder.cvMovieFav.setOnClickListener {
            listener?.onItemClick(tableMovie.get(position))
        }
    }

    fun setFavouriteMovie(tableMovie: MutableList<TableMovie>){
        this.tableMovie = tableMovie
        notifyDataSetChanged()
    }

    fun getMovieAt(position: Int):TableMovie{
        return tableMovie.get(position)
    }

    class HolderData(val view: View):RecyclerView.ViewHolder(view){
        val tvTitleFavMov:TextView = view.findViewById(R.id.tvTitleFavMov)
        val tvDescFavMov:TextView = view.findViewById(R.id.tvDescFavMov)
        val tvRatingFavMov:TextView = view.findViewById(R.id.tvRatingFavMov)
        val ivPhotoFavMov:ImageView = view.findViewById(R.id.ivPhotoFavMov)
        val cvMovieFav:CardView = view.findViewById(R.id.cvMovieFav)
    }

    interface OnClickListener {
        fun onItemClick(tableMovie: TableMovie)
    }

    fun setOnClickListenerItem(listener:OnClickListener){
        this.listener =listener
    }
}
