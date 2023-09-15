package com.mahdicen.knowmovies.nav

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.mahdicen.knowmovies.MainRepository
import com.mahdicen.knowmovies.MainViewModel
import com.mahdicen.knowmovies.api.APIKEY
import com.mahdicen.knowmovies.api.ApiServiceSingletone
import com.mahdicen.knowmovies.data.RawMovie
import com.mahdicen.knowmovies.databinding.FragmentMovieBinding
import com.mahdicen.knowmovies.local.MyDatabase
import com.mahdicen.knowmovies.util.MainViewModelFactory
import com.mahdicen.knowmovies.util.asyncRequest
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.time.Year

class MovieFragment : Fragment() {
    lateinit var binding :FragmentMovieBinding
    lateinit var mainViewModel :MainViewModel
    lateinit var thisMovie :RawMovie
    lateinit var title :String
    lateinit var year :String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        thisMovie = MovieFragmentArgs.fromBundle(requireArguments()).movie
        bindAllData()

    }

    @SuppressLint("SetTextI18n")
    private fun bindAllData() {


        Glide
            .with(requireContext())
            .load(thisMovie.poster)
            .into(binding.imgPoster)

        binding.nameTitle.text = thisMovie.title
        binding.txtDirector.text = thisMovie.director
        binding.txtRate.text = thisMovie.imdbRating
        binding.txtTimeRange.text = thisMovie.runtime

        binding.txtPlot.text = thisMovie.plot
        binding.txtActors.text = thisMovie.actors

        binding.txtCountryAndLanguage.text = "${thisMovie.country} : ${thisMovie.language}"

    }

}