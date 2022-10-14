package com.hugh.presentation.ui.keyboard.clipBoard

import androidx.recyclerview.widget.RecyclerView
import com.hugh.model.ClipBoardData
import com.hugh.presentation.databinding.ClipItemBinding

class ClipViewHolder(private val binding: ClipItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ClipBoardData) {
        binding.clip = data
        binding.executePendingBindings()
    }

}