package com.example.newsapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.remote.ApiClient
import com.example.newsapp.data.response.ArticlesItem
import com.example.newsapp.data.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews: RecyclerView
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        rvNews = findViewById(R.id.rvNews)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getNews()

        initRecyclerView()

    }

    private fun initRecyclerView() {
        adapter = NewsAdapter {
//            onclick
        }
        rvNews.layoutManager = LinearLayoutManager(this)

        rvNews.adapter = adapter
    }

    private fun getNews() {
        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("MainActivity", response.body().toString())

                if (response.isSuccessful){
                    val articles: List<ArticlesItem> = response.body()?.articles as List<ArticlesItem>
                    adapter.setNews(articles)
                }
            }

            override fun onFailure(p0: Call<NewsResponse>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}