package com.udacity.asteroidradar.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.model.Asteroid
import com.udacity.asteroidradar.model.PictureOfDay
import com.udacity.asteroidradar.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaApiService {

    @GET("neo/rest/v1/feed")
    suspend fun getAsteroids(
//        @Query("start_date") startDate: String,
//        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): List<Asteroid>

    @GET("planetary/apod")
    suspend fun getPictureOfDay(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): PictureOfDay

}

object NetworkService {
    private var nasaApiService: NasaApiService? = null

    private val retrofit: Retrofit
        get() {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(OkHttpClient())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

    fun get(): NasaApiService {
        if (nasaApiService == null) {
            nasaApiService = retrofit.create(NasaApiService::class.java)
        }
        return nasaApiService!!
    }
}