package com.farhanfr.movielibraryapp.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.farhanfr.movielibraryapp.room.table.TableMovie

@Dao
interface MovieDao {

    @Insert
    fun addFavouriteMovie(tableMovie: TableMovie)

    @Delete
    fun deleteFavouriteMovie(tableMovie: TableMovie)

    @Query("SELECT * FROM MovieTable")
    fun getMovieFavourite():LiveData<MutableList<TableMovie>>



}