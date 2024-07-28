package com.example.newsapp.data.remote

import com.example.newsapp.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET ("top-headlines?country=id&apiKey=47b3091ffc8b49e1b29120a3c2d78fcc")
    fun getNews () : Call<NewsResponse>
}