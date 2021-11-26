package com.example.newapp.model

import android.media.Image

data class News(
    var title: String,
    var content: String?,
    var author: String?,
    var url: String?,
    var urlToImage: String?
)