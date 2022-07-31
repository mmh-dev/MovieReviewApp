package com.mmh.moviereviewapp.di

import android.app.Application
import android.content.Context
import com.mmh.moviereviewapp.data.repository.MovieRepository
import com.mmh.moviereviewapp.data.retrofitApis.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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