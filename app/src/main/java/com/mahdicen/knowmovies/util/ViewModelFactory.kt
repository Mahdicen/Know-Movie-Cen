package com.mahdicen.knowmovies.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahdicen.knowmovies.MainRepository
import com.mahdicen.knowmovies.MainViewModel

class MainViewModelFactory(private val mainRepository: MainRepository)
    :ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(mainRepository) as T
    }

}