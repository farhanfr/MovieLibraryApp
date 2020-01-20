package com.farhanfr.movielibraryapp.room.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MovieTable")
data class TableMovie (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id:Int? = null,
    @ColumnInfo(name = "movie_id") var movie_id:Int? =null,
    @ColumnInfo(name = "poster_path") var poster_path: String?=null,
    @ColumnInfo(name = "title") var title: String?=null,
    @ColumnInfo(name = "overview") var overview: String?=null,
    @ColumnInfo(name = "vote_average") var vote_average: String?=null

)