package com.mahdicen.knowmovies.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiServiceSingletone {

    var apiService: ApiService? = null
        get() {

            if (field == null) {

                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

                apiService = retrofit.create(ApiService::class.java)

            }

            return field

        }

}