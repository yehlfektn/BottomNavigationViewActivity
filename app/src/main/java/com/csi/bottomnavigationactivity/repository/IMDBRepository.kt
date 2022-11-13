package com.csi.bottomnavigationactivity.repository

import com.csi.bottomnavigationactivity.network.ApiService

class IMDBRepository(
    private val apiService: ApiService
) {
    fun getMovies(search: String) = apiService.getMoviesBySearch(search = search)
}