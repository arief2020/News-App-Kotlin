package com.example.newsapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.response.ArticlesItem

class NewsAdapter(private val listener: (ArticlesItem) -> Unit)  :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

        private var news = listOf<ArticlesItem>()

    fun setNews (news: List<ArticlesItem>){
        this.news = news
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return  ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle) // Ensure you have a TextView with this ID in item_news.xml

        fun bind(article: ArticlesItem) {
            tvTitle.text = article.title // Set the title text

            itemView.setOnClickListener {
                listener(article) // Invoke the listener with the current article
            }
        }
    }
}