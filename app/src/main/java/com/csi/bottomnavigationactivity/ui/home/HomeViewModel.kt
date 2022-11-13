package com.csi.bottomnavigationactivity.ui.home

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

class HomeViewModel(
    private val repository: NoteRepository,
    private val imdbRepository: IMDBRepository
) : ViewModel() {

    // on below line we are creating a variable
    // for our all notes list and repository
    val allNotes: LiveData<List<Note>> = repository.allNotes

    private val _movieResult = MutableLiveData<IMDBResult>()
    val movieResult: LiveData<IMDBResult> = _movieResult

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(note)
        }
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(note)
        }
    }

    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(note)
        }
    }

    fun getMovies(search: String) {
        viewModelScope.launch {
            imdbRepository.getMovies(search).enqueue(object : Callback<IMDBResult> {
                override fun onResponse(call: Call<IMDBResult>, response: Response<IMDBResult>) {
                    _movieResult.value = response.body()
                    Timber.e("GetMovies: ${response.body()}")
                }

                override fun onFailure(call: Call<IMDBResult>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
}