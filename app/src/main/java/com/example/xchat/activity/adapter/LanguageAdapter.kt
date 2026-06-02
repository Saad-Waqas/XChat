package com.example.xchat.activity.adapter

import android.annotation.SuppressLint
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.xchat.R
import com.example.xchat.activity.extention.LanguageItem
import com.example.xchat.activity.extention.fillLanguages
import java.util.Locale

class LanguageAdapter(
    private val savedCode: String?,
    private var fromSetting: Boolean = false,
    private val onLanguageSelected: (LanguageItem) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    companion object {
        // use for save state scroll of recyclerview
        var instanceState: Parcelable? = null
    }

    val languageList = fillLanguages().apply {
        val deviceLangCode = Locale.getDefault().language
        val priorityCode = savedCode ?: deviceLangCode
        if (fromSetting){
            sortWith(
                compareByDescending<LanguageItem> { it.code == priorityCode }
                    .thenByDescending { it.code == (if (priorityCode != "en") "en" else "") }
                    .thenBy { it.languageName }
            )
        }
    }
    private var selectedPosition = -1

    init {
        selectedPosition = if (savedCode != null) {
            languageList.indexOfFirst { it.code == savedCode }
        } else {
            -1
        }
    }

    val selectedLanguage: LanguageItem?
        get() = if (selectedPosition != -1) languageList[selectedPosition] else null

    inner class LanguageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flagImage: ImageView = itemView.findViewById(R.id.imageFlag)
        val languageText: TextView = itemView.findViewById(R.id.textCountry)
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)
        val mainBg: ConstraintLayout = itemView.findViewById(R.id.mainConstBg)
        val languageTextShort: TextView = itemView.findViewById(R.id.languageText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.language_item, parent, false)
        return LanguageViewHolder(view)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val item = languageList[position]

        holder.languageText.text = item.languageName
        holder.languageTextShort.text = item.languageBracket
        holder.flagImage.setImageResource(item.flagResId)

        holder.radioButton.isChecked = position == selectedPosition

        // Change text color depending on selection
        val context = holder.itemView.context
        // Apply color and background based on selection
        val isSelected = holder.radioButton.isChecked
        val selectedBg = ContextCompat.getDrawable(context, R.drawable.language_bg_select)
        val unselectedBg = ContextCompat.getDrawable(context, R.drawable.language_bg_unselect)

        holder.mainBg.background = if (isSelected) selectedBg else unselectedBg

        // Handle clicks
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
            onLanguageSelected(item)
        }
    }

    override fun getItemCount() = languageList.size
}