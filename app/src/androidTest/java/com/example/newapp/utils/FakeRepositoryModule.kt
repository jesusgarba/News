package com.example.newapp.utils

import com.example.newapp.di.RepositoryModule
import com.example.newapp.model.News
import com.example.newapp.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
class FakeRepositoryModule {
    @Provides
    @Singleton
    fun providerNewsRepository():NewsRepository =
        object :  NewsRepository{
            val news = arrayListOf(
                News("Title", "Content description", "", "", "https://placeHolder"),
                News("Title2", "Content description", "", "", "https://placeHolder")
            )
            override suspend fun getNews(country: String): List<News> {
                return news
            }

            override fun getNew(title: String): News {
                return news[0]
            }

        }
}