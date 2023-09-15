package com.mahdicen.knowmovies.api

import com.mahdicen.knowmovies.data.RawMovie
import com.mahdicen.knowmovies.data.RawSearch
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun searchMovies(
        @Query("apikey") apikey :String ,
        @Query("s") search :String
    ) :Single<RawSearch>

    @GET(".")
    fun getMovie(
        @Query("apikey") apikey :String ,
        @Query("t") title :String,
        @Query("y") year :Int,
        @Query("plot") plot :String
    ) :Single<RawMovie>

}