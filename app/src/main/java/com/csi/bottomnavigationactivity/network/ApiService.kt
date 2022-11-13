package com.csi.bottomnavigationactivity.network


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(".")
    fun getMoviesBySearch(
        @Query("apikey") apiKey: String = "57a88c99",
        @Query("s") search: String
    ): Call<IMDBResult>
}