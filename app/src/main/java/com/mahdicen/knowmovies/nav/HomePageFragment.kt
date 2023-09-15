package com.mahdicen.knowmovies.nav

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mahdicen.knowmovies.MainRepository
import com.mahdicen.knowmovies.MainViewModel
import com.mahdicen.knowmovies.R
import com.mahdicen.knowmovies.util.MainViewModelFactory
import com.mahdicen.knowmovies.adapters.HomeAdapter
import com.mahdicen.knowmovies.api.APIKEY
import com.mahdicen.knowmovies.api.ApiService
import com.mahdicen.knowmovies.api.ApiServiceSingletone
import com.mahdicen.knowmovies.data.RawMovie
import com.mahdicen.knowmovies.databinding.FragmentHomePageBinding
import com.mahdicen.knowmovies.local.FirstData
import com.mahdicen.knowmovies.local.Movie
import com.mahdicen.knowmovies.local.MyDatabase
import com.mahdicen.knowmovies.util.asyncRequest
import dagger.android.support.DaggerFragment
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomePageFragment : Fragment(), HomeAdapter.MovieEvents {
    lateinit var binding: FragmentHomePageBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomePageBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewModel =>
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(
                MainRepository(
                    ApiServiceSingletone.apiService!!,
                    MyDatabase.getDatabase(requireContext()).movieDao,
                    MyDatabase.getDatabase(requireContext()).recentDao
                )
            )
        )[MainViewModel::class.java]

        // First Run =>
        val shared = requireActivity().getSharedPreferences("First", Context.MODE_PRIVATE)
        if (shared.getBoolean("firstData", true)) {
            firstRun()
            shared.edit().putBoolean("firstData", false).apply()
            Log.v("testFirstData", "succedd")
        }

        mainViewModel.getAllData().observe(viewLifecycleOwner) {
            homeAdapter = HomeAdapter(ArrayList(it), this)
            binding.recyclerMain.adapter = homeAdapter
            binding.recyclerMain.layoutManager =
                GridLayoutManager(requireContext(), 2)
            Log.v("testDatabase", it.size.toString())
        }

    }

    private fun firstRun() {
        initRecycler()
        mainViewModel.insertAll(FirstData.firstData)
    }
    private fun initRecycler() {

        val data = arrayListOf<Movie>()
        homeAdapter = HomeAdapter(data, this)
        binding.recyclerMain.adapter = homeAdapter
        binding.recyclerMain.layoutManager =
            GridLayoutManager(requireContext(), 2)

    }

    override fun clickOnMovie(title: String, year: Int) {

        mainViewModel
            .getMovie(APIKEY , title , year , "full")
            .asyncRequest()
            .subscribe(object :SingleObserver<RawMovie>{
                override fun onSubscribe(d: Disposable) {

                }
                override fun onError(e: Throwable) {

                }
                override fun onSuccess(t: RawMovie) {
                    findNavController().navigate(
                        HomePageFragmentDirections.actionHomePageFragmentToMovieFragment(t)
                            .setMovie(t)
                    )
                }

            })

    }

}