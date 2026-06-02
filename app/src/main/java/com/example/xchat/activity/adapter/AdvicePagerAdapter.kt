package com.example.xchat.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xchat.R
import com.example.xchat.activity.extention.AdviceData

class AdvicePagerAdapter(
    private val items: List<AdviceData>
) : RecyclerView.Adapter<AdvicePagerAdapter.AdviceViewHolder>() {

    inner class AdviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageAdvice)
        val postImg: ImageView = itemView.findViewById(R.id.postImg)
        val textView: TextView = itemView.findViewById(R.id.textAdvice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdviceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.advice_item, parent, false)
        return AdviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdviceViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.imageResId)
        holder.postImg.setImageResource(item.postIg)
        holder.textView.text = item.text
    }

    override fun getItemCount() = items.size
}