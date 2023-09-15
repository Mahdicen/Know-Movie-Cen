package com.mahdicen.knowmovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.mahdicen.knowmovies.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()
{
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemReselectedListener {  }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {
                    findNavController(R.id.my_nav_host_fragment).popBackStack()
                    findNavController(R.id.my_nav_host_fragment).navigate(R.id.homePageFragment)
                }
                R.id.search -> {
                    findNavController(R.id.my_nav_host_fragment).popBackStack()

                    findNavController(R.id.my_nav_host_fragment).navigate(R.id.searchFragment)
                }
                R.id.profile -> {
                    findNavController(R.id.my_nav_host_fragment).popBackStack()
                    findNavController(R.id.my_nav_host_fragment).navigate(R.id.profileFragment)
                }
            }
            true
        }
    }

}