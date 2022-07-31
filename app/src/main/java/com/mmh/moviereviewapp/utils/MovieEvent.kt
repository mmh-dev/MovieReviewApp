package com.mmh.moviereviewapp.utils

sealed class MovieEvent {
    class Success<T>(val resultData: T): MovieEvent()
    class Error<String>(val errorText: String): MovieEvent()
    object Empty: MovieEvent()
}