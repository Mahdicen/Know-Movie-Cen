package com.mahdicen.knowmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mahdicen.knowmovies.data.RawMovie
import com.mahdicen.knowmovies.data.RawSearch
import com.mahdicen.knowmovies.local.Movie
import com.mahdicen.knowmovies.local.Recent
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val mainRepository: MainRepository) :ViewModel() {

    lateinit var compositeDisposable: CompositeDisposable

    init {
        Log.v("test" , "Created")
    }

    fun insertAll(data :List<Movie>) {
        mainRepository.insertAll(data)
    }

    fun getAllData() : LiveData<List<Movie>> {
        return mainRepository.getAllData()
    }

    fun getAllRecent() : LiveData<List<Recent>> {
        return mainRepository.getAllRecent()
    }

    fun getMovie(apikey :String , title :String , year :Int ,plot :String ) : Single<RawMovie> {
        return mainRepository.getMovie(apikey , title , year , plot)
    }

    fun searchMovie(apikey: String , search :String) : Single<RawSearch> {
        return mainRepository.searchMovie(apikey , search)
    }

}