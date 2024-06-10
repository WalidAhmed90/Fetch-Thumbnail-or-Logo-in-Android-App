package com.example.fetchingthumbnailsorlogos

import retrofit2.http.GET
import retrofit2.http.Url


interface ApiService {
    @GET
    suspend fun fetchHtml(@Url url: String): String
}
