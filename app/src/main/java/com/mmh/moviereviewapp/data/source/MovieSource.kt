package com.mmh.moviereviewapp.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mmh.moviereviewapp.data.entities.Movie
import com.mmh.moviereviewapp.data.repository.MovieRepository

class MovieSource(
    private val repository: MovieRepository
) : PagingSource<Int, Movie> (){
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getUsers(nextPageNumber, 10)
            LoadResult.Page(
                data = response.users,
                prevKey = null,
                nextKey = if (response.users.isNotEmpty()) response.page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}