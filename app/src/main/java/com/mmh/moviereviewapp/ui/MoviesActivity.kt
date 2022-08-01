package com.mmh.moviereviewapp.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmh.moviereviewapp.data.entities.Movie
import com.mmh.moviereviewapp.data.network.MovieApi
import com.mmh.moviereviewapp.databinding.ActivityMoviesBinding
import com.mmh.moviereviewapp.utils.MovieEvent
import com.mmh.moviereviewapp.utils.showToast
import com.mmh.moviereviewapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()
    private val movieAdapter = MovieAdapter()
    @Inject
    lateinit var api: MovieApi

    private val binding: ActivityMoviesBinding by lazy {
        ActivityMoviesBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.reviewsRv.apply {
            layoutManager = LinearLayoutManager(this@MoviesActivity)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        binding.shimmer.startShimmerAnimation()

        viewModel.getMovies()
        lifecycleScope.launch {
            viewModel.movieList.collect { event ->
                when (event) {
                    is MovieEvent.Success<*> -> {
                        val movies = event.resultData as MutableList<Movie>
                        movieAdapter.submitList(movies)
                        binding.apply {
                            shimmer.stopShimmerAnimation()
                            shimmer.visibility = View.GONE
                            reviewsRv.visibility = View.VISIBLE
                        }
                    }
                    is MovieEvent.Error<*> -> {
                        showToast(event.errorText as String)
                    }
                    else -> Unit
                }
            }
        }
    }
}