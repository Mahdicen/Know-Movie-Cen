package com.mahdicen.knowmovies.nav

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mahdicen.knowmovies.MainRepository
import com.mahdicen.knowmovies.MainViewModel
import com.mahdicen.knowmovies.R
import com.mahdicen.knowmovies.adapters.SearchAdapter
import com.mahdicen.knowmovies.api.APIKEY
import com.mahdicen.knowmovies.api.ApiServiceSingletone
import com.mahdicen.knowmovies.data.RawMovie
import com.mahdicen.knowmovies.data.RawSearch
import com.mahdicen.knowmovies.databinding.FragmentSearchBinding
import com.mahdicen.knowmovies.local.MyDatabase
import com.mahdicen.knowmovies.util.MainViewModelFactory
import com.mahdicen.knowmovies.util.asyncRequest
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SearchFragment : Fragment() , SearchAdapter.SearchMovieEvent {
    lateinit var binding: FragmentSearchBinding
    lateinit var mainViewModel: MainViewModel
    lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
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

        binding.edtSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                mainViewModel
                    .searchMovie(APIKEY, p0!!)
                    .asyncRequest()
                    .subscribe(object : SingleObserver<RawSearch> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onError(e: Throwable) {

                        }

                        override fun onSuccess(t: RawSearch) {
                            val body = t.search ?: listOf<RawSearch.Search>()
                            binding.imgLego.visibility = View.GONE
                            binding.txtKnow.visibility = View.GONE
                            binding.recyclerSearch.visibility = View.VISIBLE

                            if (body.size >= 6) {
                                val data = listOf<RawSearch.Search>(
                                    body[0]!!, body[1]!!, body[2]!!,
                                    body[3]!!, body[4]!!, body[5]!!
                                )
                                setSearchData(data)
                            } else if (body.isEmpty()) {
                                Snackbar
                                    .make(
                                        binding.root,
                                        "There is no such a thing!",
                                        Snackbar.LENGTH_LONG
                                    )
                                    .setBackgroundTint(ContextCompat
                                        .getColor(requireContext() , R.color.red))
                                    .setTextColor(ContextCompat
                                        .getColor(requireContext() , R.color.white))
                                    .show()
                            } else {
                                setSearchData(body)
                            }
                        }

                    })
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0!!.isEmpty()) {
                    binding.recyclerSearch.visibility = View.GONE
                    binding.imgLego.visibility = View.VISIBLE
                    binding.txtKnow.visibility = View.VISIBLE
                }
                return true
            }

        })

    }

    private fun setSearchData(t: List<RawSearch.Search?>?) {
        searchAdapter = SearchAdapter(ArrayList(t!!) , this)
        binding.recyclerSearch.adapter = searchAdapter
        binding.recyclerSearch.layoutManager =
            LinearLayoutManager(requireContext())
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
                        SearchFragmentDirections.actionSearchFragmentToMovieFragment(t)
                            .setMovie(t)
                    )
                }

            })

    }

}