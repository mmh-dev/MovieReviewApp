package com.mmh.moviereviewapp.data.entities

data class MovieResponse(
    var status: String? = null,
    var has_more: Boolean? = null,
    var num_results: Int? = null,
    var results: MutableList<Movie> = mutableListOf()
)