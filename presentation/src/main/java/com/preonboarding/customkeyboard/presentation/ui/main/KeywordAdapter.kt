package com.preonboarding.customkeyboard.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.preonboarding.customkeyboard.presentation.databinding.ItemKeywordBinding
import com.preonboarding.customkeyboard.presentation.model.Keyword

class KeywordAdapter : RecyclerView.Adapter<KeywordAdapter.KeywordViewHolder>() {

    private val keywords = Keyword.getDummyKeywords()

    inner class KeywordViewHolder(private val binding: ItemKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Keyword) {
            binding.keyword = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder {
        return KeywordViewHolder(
            ItemKeywordBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: KeywordViewHolder, position: Int) =
        holder.bind(keywords[position])

    override fun getItemCount(): Int = keywords.size
}
