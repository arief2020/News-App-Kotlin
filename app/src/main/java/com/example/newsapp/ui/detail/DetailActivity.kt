package com.example.newsapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.data.response.ArticlesItem

class DetailActivity : AppCompatActivity() {

    companion object{
        val EXTRA_NEWS = "extraNews"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

        news?.let{initView(it)}


    }

    private fun initView(news: ArticlesItem) {
        val tvTitleDetail: TextView = this.findViewById(R.id.tvTitleDetail)
        val tvSourceDetail: TextView = this.findViewById(R.id.tvSourceNameDetail)
        val tvAuthorDetail: TextView = this.findViewById(R.id.tvAuthorDetail)
        val tvDescriptionDetail: TextView = this.findViewById(R.id.tvDescriptionDetail)
        val ivDetail: ImageView = this.findViewById(R.id.ivNewsDetail)
        val btnToNews: Button = this.findViewById(R.id.btnToNews)



        Log.d("DetailActivity", news!!.author.toString())

        tvTitleDetail.text = news.title
        tvAuthorDetail.text = news.author
        tvSourceDetail.text = news.source?.name
        tvDescriptionDetail.text = news.description
        Glide.with(this).load(news.urlToImage)
            .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
            .into(ivDetail)
        btnToNews.setOnClickListener{
            openWebPage(news.url)
        }
    }

    private fun openWebPage(url: String?) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}