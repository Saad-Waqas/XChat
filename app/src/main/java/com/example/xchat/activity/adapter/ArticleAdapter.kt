package com.example.xchat.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xchat.R
import com.example.xchat.activity.extention.AdviceData

class ArticleAdapter(
private val items: List<AdviceData>
) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageArticle: ImageView = itemView.findViewById(R.id.imageArticle)
        val textView: TextView = itemView.findViewById(R.id.textArticle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val item = items[position]
        holder.imageArticle.setImageResource(item.imageResId)
        holder.textView.text = item.text
    }

    override fun getItemCount() = items.size
}