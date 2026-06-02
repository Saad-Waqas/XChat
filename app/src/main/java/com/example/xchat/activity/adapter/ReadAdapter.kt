package com.example.xchat.activity.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xchat.R
import com.example.xchat.activity.extention.ReadData


class ReadAdapter(
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEAD = 0
    private val TYPE_BODY = 1

    sealed class ReadItem {
        data class Header(val text: String) : ReadItem()
        data class Body(val text: String) : ReadItem()
    }

    val items = mutableListOf<ReadItem>()

    private var selectedTone: ReadData? = null

    fun submitList(sections: List<ReadData>) {

        items.clear()

        sections.forEach { section ->
            items.add(ReadItem.Header(section.head))
            items.add(ReadItem.Body(section.body))
        }

        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ReadItem.Header -> TYPE_HEAD
            is ReadItem.Body -> TYPE_BODY
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            TYPE_HEAD -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.read_heading, parent, false)
                HeaderVH(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.read_body, parent, false)
                ToneVH(view)
            }
        }
    }

    override fun getItemCount() = items.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is HeaderVH -> {
                val item = items[position] as ReadItem.Header
                holder.bind(item.text)
            }

            is ToneVH -> {
                val item = items[position] as ReadItem.Body
                holder.bind(item.text)
            }
        }
    }

    inner class HeaderVH(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.headTxt)

        fun bind(text: String) {
            title.text = text
        }
    }
    inner class ToneVH(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.bodyTxt)

        fun bind(text: String) {
            title.text = text
        }
    }

}