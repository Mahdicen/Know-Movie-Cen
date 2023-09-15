package com.mahdicen.knowmovies.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie_table")
    fun getTopMovies() :LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE , entity = Movie::class)
    fun insertAll(data :List<Movie>)

}