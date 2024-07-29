package com.example.newsapp.data.remote

import com.example.newsapp.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {
    @GET ("everything?q=bitcoin&apiKey=47b3091ffc8b49e1b29120a3c2d78fcc")
    fun getNews () : Call<NewsResponse>

    @GET ("top-headlines?country=us&apiKey=47b3091ffc8b49e1b29120a3c2d78fcc")
    fun getNewsHeadline () : Call<NewsResponse>
}
//@GET ("everything?q=bitcoin&apiKey=47b3091ffc8b49e1b29120a3c2d78fcc")
//@GET ("top-headlines?country=us&apiKey=47b3091ffc8b49e1b29120a3c2d78fcc")