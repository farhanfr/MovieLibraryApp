package com.farhanfr.movielibraryapp.ui.tv.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.farhanfr.movielibraryapp.R
import com.farhanfr.movielibraryapp.retrofit.model.TvModel
import com.farhanfr.movielibraryapp.ui.movie.adapter.MovieListAdapter
import com.squareup.picasso.Picasso

class TvListAdapter(val context: Context): RecyclerView.Adapter<TvListAdapter.HolderTvData>() {

    private var tvModel:ArrayList<TvModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvListAdapter.HolderTvData {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rc_tv_list,parent,false)
        return HolderTvData(view)
    }
    override fun getItemCount(): Int {
        return tvModel.size
    }
    override fun onBindViewHolder(holder: TvListAdapter.HolderTvData, position: Int) {
        Picasso.with(context).load("https://image.tmdb.org/t/p/w185/"+tvModel.get(position).poster_path).into(holder.ivTvPhoto)
        holder.tvTitleTv.text = tvModel.get(position).original_name
        holder.tvDescriptionTv.text = tvModel.get(position).overview
        holder.tvRatingTv.text = tvModel.get(position).vote_average.toString()
    }

    fun setTvListAdapter(tvModel: ArrayList<TvModel>){
        this.tvModel = tvModel
        notifyDataSetChanged()

    }

    class HolderTvData(val view: View):RecyclerView.ViewHolder(view){
        val ivTvPhoto: ImageView = view.findViewById(R.id.ivTvPhoto)
        val tvTitleTv: TextView = view.findViewById(R.id.tvTitleTv)
        val tvDescriptionTv: TextView = view.findViewById(R.id.tvDescriptionTv)
        val tvRatingTv: TextView = view.findViewById(R.id.tvRatingTv)
    }
}