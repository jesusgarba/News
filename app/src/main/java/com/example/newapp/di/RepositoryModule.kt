package com.example.newapp.di

import com.example.newapp.provider.NewsProvider
import com.example.newapp.repository.NewsRepository
import com.example.newapp.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import retrofit2.Retrofit

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providerNewsRepository(provider: NewsProvider): NewsRepository =
        NewsRepositoryImpl(provider)


}
