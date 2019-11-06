package com.app.firstappkotlin.service

import com.app.firstappkotlin.model.ResponseUser
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIServices {

    @GET("repos")
    fun getRepos(): Call<List<ResponseUser>>

    companion object {

        var BASE_URL = "https://api.github.com/users/farizdotid/"

        fun create() : APIServices {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIServices::class.java)

        }
    }

}