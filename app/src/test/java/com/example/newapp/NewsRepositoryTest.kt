package com.example.newapp

import com.example.newapp.provider.NewsProvider
import com.example.newapp.repository.NewsRepositoryImpl
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NewsRepositoryTest {
    private val mockWebServer = MockWebServer()

    private val newsProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsProvider::class.java)

    private val newsRepository = NewsRepositoryImpl(newsProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Top headlines response is correct`() {
        mockWebServer.enqueueResponse("top_headlines.json")

        runBlocking {
            val articles = newsRepository.getNews("US")
            assertEquals(20, articles.size)
            assertEquals("Quentin Webb, Suryatapa Bhattacharya", articles[0].author)
            assertEquals("Benjamin Raven | braven@mlive.com", articles[19].author)
        }
    }
}


fun MockWebServer.enqueueResponse(filePath: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}

