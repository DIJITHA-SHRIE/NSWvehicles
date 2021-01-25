package com.ntt.nttcarregistration.Network

import com.ntt.nttcarregistration.Model.Registration
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET

private val service: Network by lazy {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://dl.dropboxusercontent.com/s/wcp5aajrrx533bp/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(Network::class.java)
}

fun getNetworkService() = service

interface Network {

    @GET("snsw_registrations_api.json")
    suspend fun  fetchRegistration():Registration


}