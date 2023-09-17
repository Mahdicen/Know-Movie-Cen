package com.mahdicen.knowmovies

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mahdicen.knowmovies.databinding.ActivityMainBinding
import com.mahdicen.knowmovies.util.NetworkChecker


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // BroadCasting connectivity changes =>
        val broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                if (NetworkChecker(this@MainActivity).isInternetConnected) {
                    Snackbar.make(
                        binding.root,
                        "Internet Connection Succeed!",
                        Snackbar.LENGTH_LONG
                    )
                        .setBackgroundTint(ContextCompat
                            .getColor(this@MainActivity , R.color.red))
                        .setTextColor(ContextCompat
                            .getColor(this@MainActivity , R.color.white))
                        .show()
                } else {
                    Snackbar.make(
                        binding.root,
                        "Internet Connection Failed!!!",
                        Snackbar.LENGTH_LONG
                    )
                        .setBackgroundTint(ContextCompat
                            .getColor(this@MainActivity , R.color.red))
                        .setTextColor(ContextCompat
                            .getColor(this@MainActivity , R.color.white))
                        .setAction("Retry") {
                            onReceive(this@MainActivity , Intent())
                        }
                        .show()
                }
            }
        }
        registerReceiver(
            broadcastReceiver,
            IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        )

        binding.bottomNavigationView.setOnItemReselectedListener { }
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
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