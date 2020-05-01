package com.acrony.kotlinregistration

import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseUrl:String): Retrofit? {
        val gson = GsonBuilder().setLenient().create()
        if (retrofit == null)
        {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return retrofit
    }
}