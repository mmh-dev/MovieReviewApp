package com.mmh.moviereviewapp.data.retrofitApis

import com.mmh.moviereviewapp.data.entities.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("all.json")
    fun getAllReviews(
        @Query("api-key") apiKey: String
    ): Call<MovieResponse>
}