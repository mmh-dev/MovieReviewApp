package com.mmh.moviereviewapp.data.repository

import com.mmh.moviereviewapp.BuildConfig
import com.mmh.moviereviewapp.data.network.MovieApi
import com.mmh.moviereviewapp.utils.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val api: MovieApi
) {
    fun getAllMovies(): Resource<Any?> {
        val response = api.getAllReviews(BuildConfig.Api_Key)
        val result = response.execute()
        return if (result.isSuccessful) {
            Resource.Success(result.body()?.results)
        } else {
            Resource.Error(result.message())
        }
    }
}