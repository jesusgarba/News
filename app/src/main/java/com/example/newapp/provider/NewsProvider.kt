package com.example.newapp.provider

import com.example.newapp.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "6357f74bd68f43bd967fbdc7ddf0a5cc"

interface NewsProvider {

    @GET("top-headlines?api = $API_KEY")
    suspend fun topHeadLines(@Query("country")country: String): Response<NewsApiResponse>
}