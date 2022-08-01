package com.mmh.moviereviewapp.di

import com.mmh.moviereviewapp.data.repository.MovieRepository
import com.mmh.moviereviewapp.data.network.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        api: MovieApi
    ): MovieRepository = MovieRepository(api)
}