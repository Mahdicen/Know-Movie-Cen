package com.mahdicen.knowmovies.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable

@Dao
interface RecentDao {

    @Query("SELECT * FROM recent_table")
    fun getRecentMovies() :LiveData<List<Recent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: Recent)

}