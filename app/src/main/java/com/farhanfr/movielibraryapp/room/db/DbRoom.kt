package com.farhanfr.movielibraryapp.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.farhanfr.movielibraryapp.room.dao.MovieDao
import com.farhanfr.movielibraryapp.room.table.TableMovie

@Database(entities = [TableMovie::class],version = 1)
abstract class DbRoom: RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var instance: DbRoom? = null
        fun getInstance(context: Context): DbRoom {
            if (instance == null) {
                synchronized(DbRoom::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DbRoom::class.java, "RoomMovieLibraryDb"
                    )
                        .fallbackToDestructiveMigration()
//                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }
    }
}