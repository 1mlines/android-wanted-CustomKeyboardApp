package com.hugh.presentation.ui.keyboard.clipBoard

import androidx.recyclerview.widget.RecyclerView
import com.hugh.model.ClipBoardData
import com.hugh.presentation.action.clipAction.ClipBoardActor
import com.hugh.presentation.databinding.ClipItemBinding

/**
 * Created by 서강휘
 */

class ClipViewHolder(
    private val binding: ClipItemBinding,
    private val clipBoardActor: ClipBoardActor
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(clip: ClipBoardData) {
        binding.clip = clip
        binding.executePendingBindings()

        binding.clipText.setOnClickListener {
            clipBoardActor.paste(clip.text)
        }

        binding.deleteFrameLayout.rootView.setOnClickListener {
            clipBoardActor.deleteClipData(clip.id)
        }
    }

}