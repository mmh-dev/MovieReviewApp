package com.mmh.moviereviewapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmh.moviereviewapp.data.entities.Movie
import com.mmh.moviereviewapp.data.repository.MovieRepository
import com.mmh.moviereviewapp.utils.MovieEvent
import com.mmh.moviereviewapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {



    private val _movieList = MutableStateFlow<MovieEvent>(MovieEvent.Empty)
    val movieList: StateFlow<MovieEvent> = _movieList

    fun getMovies() {
        viewModelScope.launch (Dispatchers.IO) {
            when (val result = repository.getAllMovies()) {
                is Resource.Success -> {
                    val movies = result.data as MutableList<Movie>
                    _movieList.value = MovieEvent.Success(movies)
                }
                is Resource.Error -> {
                    val errorMessage = result.message
                    _movieList.value = MovieEvent.Error(errorMessage)
                }

            }
        }
    }
}