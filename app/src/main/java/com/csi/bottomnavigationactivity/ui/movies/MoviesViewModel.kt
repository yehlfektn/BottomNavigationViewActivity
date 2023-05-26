package com.csi.bottomnavigationactivity.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csi.bottomnavigationactivity.db.Note
import com.csi.bottomnavigationactivity.network.IMDBResult
import com.csi.bottomnavigationactivity.repository.IMDBRepository
import com.csi.bottomnavigationactivity.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MoviesViewModel(
    private val imdbRepository: IMDBRepository
) : ViewModel() {

    private val _movieResult = MutableLiveData<IMDBResult>()
    val movieResult: LiveData<IMDBResult> = _movieResult

    fun getMovies(search: String) {
        viewModelScope.launch {
            imdbRepository.getMovies(search).enqueue(object : Callback<IMDBResult> {
                override fun onResponse(call: Call<IMDBResult>, response: Response<IMDBResult>) {
                    _movieResult.value = response.body()
                }
                override fun onFailure(call: Call<IMDBResult>, t: Throwable) {
                }
            })
        }
    }
}