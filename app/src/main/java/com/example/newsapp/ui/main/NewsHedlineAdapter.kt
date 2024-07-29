package com.example.newsapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp.R
import com.example.newsapp.data.response.ArticlesItem

class NewsHedlineAdapter (private val listener: (ArticlesItem) -> Unit) :
    RecyclerView.Adapter<NewsHedlineAdapter.ViewHolder>() {

    private var newsHeadline = listOf<ArticlesItem>()

    fun setNewsHeadline (news: List<ArticlesItem>){
        this.newsHeadline = news
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsHedlineAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_headline_news, parent, false)
        return  ViewHolder(view)
    }



    override fun onBindViewHolder(holder: NewsHedlineAdapter.ViewHolder, position: Int) {
        holder.bind(newsHeadline[position])
    }

    override fun getItemCount(): Int {
        return newsHeadline.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitleHeadline)
        private val ivNews: ImageView = itemView.findViewById(R.id.ivNewsHeadline)

        fun bind(article: ArticlesItem) {
            tvTitle.text = article.title

            Glide.with(itemView.context).load(article.urlToImage)
                .apply(RequestOptions().dontTransform().placeholder(R.drawable.loading_image))
                .into(ivNews)
            itemView.setOnClickListener {
                listener(article)
            }
        }
    }
}