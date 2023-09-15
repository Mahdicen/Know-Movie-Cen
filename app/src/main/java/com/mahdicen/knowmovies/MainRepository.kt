package com.mahdicen.knowmovies

import androidx.lifecycle.LiveData
import com.mahdicen.knowmovies.api.ApiService
import com.mahdicen.knowmovies.data.RawMovie
import com.mahdicen.knowmovies.data.RawSearch
import com.mahdicen.knowmovies.local.Movie
import com.mahdicen.knowmovies.local.MovieDao
import com.mahdicen.knowmovies.local.Recent
import com.mahdicen.knowmovies.local.RecentDao
import io.reactivex.Single

class MainRepository(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val recentDao: RecentDao
) {

    fun insertAll(data :List<Movie>) {
        movieDao.insertAll(data)
    }

    fun getAllData() :LiveData<List<Movie>> {
        return movieDao.getTopMovies()
    }

    fun getAllRecent() :LiveData<List<Recent>> {
        return recentDao.getRecentMovies()
    }

    fun getMovie(apikey :String , title :String , year :Int , plot :String) :Single<RawMovie> {
        return apiService
            .getMovie(apikey ,title , year ,plot)
            .doOnSuccess {
                val recent = Recent(it.imdbRating , it.poster , it.title!! , it.type , it.year)
                recentDao.addMovie(recent)
            }
    }

    fun searchMovie(apikey: String , search :String) :Single<RawSearch> {
        return apiService.searchMovies(apikey , search)
    }

}