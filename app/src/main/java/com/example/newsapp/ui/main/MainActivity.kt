package com.example.newsapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
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
import com.example.newsapp.ui.detail.DetailActivity
import com.example.newsapp.ui.detail.DetailActivity.Companion.EXTRA_NEWS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var rvNews: RecyclerView
    private lateinit var rvNewsHeadline: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var adapterHeadline: NewsHedlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Mandiri News"

        rvNews = findViewById(R.id.rvNews)
        rvNewsHeadline = findViewById(R.id.rvNewsHeadline)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getNews()

        initRecyclerView()

        getNewsHeadline()

        initRecyclerViewHeadline()

    }

    private fun initRecyclerViewHeadline() {
        adapterHeadline = NewsHedlineAdapter {
            moveActivity(it)
        }
        rvNewsHeadline.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rvNewsHeadline.adapter = adapterHeadline
    }

    private fun getNewsHeadline() {
        ApiClient.create().getNewsHeadline().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("MainActivity", response.body().toString())

                if (response.isSuccessful){
                    val articles: List<ArticlesItem> = response.body()?.articles as List<ArticlesItem>
                    adapterHeadline.setNewsHeadline(articles)
                }
            }

            override fun onFailure(response: Call<NewsResponse>, p1: Throwable) {
                Log.d("MainActivity", response.toString())
            }

        })
    }

    private fun initRecyclerView() {
        adapter = NewsAdapter {
            moveActivity(it)
        }
        rvNews.layoutManager = LinearLayoutManager(this)

        rvNews.adapter = adapter
    }

    private fun moveActivity(news: ArticlesItem) {
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra(EXTRA_NEWS, news)
        startActivity(intent)
    }

    private fun getNews() {
        ApiClient.create().getNews().enqueue(object : Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
//                Log.d("MainActivity", response.body().toString())

                if (response.isSuccessful){
                    val articles: List<ArticlesItem> = response.body()?.articles as List<ArticlesItem>
                    adapter.setNews(articles)
                }
            }

            override fun onFailure(response: Call<NewsResponse>, p1: Throwable) {
                Log.d("MainActivity", response.toString())
            }

        })
    }
}