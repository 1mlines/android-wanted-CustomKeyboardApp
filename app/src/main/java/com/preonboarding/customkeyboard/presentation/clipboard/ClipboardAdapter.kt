package com.preonboarding.customkeyboard.presentation.clipboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.preonboarding.customkeyboard.databinding.ItemClipboardBinding
import com.preonboarding.customkeyboard.domain.model.Clipboard

class ClipboardAdapter(
    private val clipboardActionListener: ClipboardActionListener,
) : ListAdapter<Clipboard, ClipboardAdapter.ClipboardViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemClipboardBinding.inflate(inflater, parent, false)
        return ClipboardViewHolder(binding, clipboardActionListener)
    }

    override fun onBindViewHolder(holder: ClipboardViewHolder, position: Int) {
        holder.bindItems(getItem(position))
    }

    class ClipboardViewHolder(
        private val binding: ItemClipboardBinding,
        private val clipboardActionListener: ClipboardActionListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Clipboard) {
            itemView.setOnClickListener {
                clipboardActionListener.copyClipData(item.clipData)
            }
            binding.apply {
                tvClipData.text = item.clipData
                ivDelete.setOnClickListener {
                    clipboardActionListener.deleteClipData(item.toEntity())
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Clipboard>() {
            override fun areItemsTheSame(oldItem: Clipboard, newItem: Clipboard): Boolean {
                return oldItem.clipData == newItem.clipData
            }

            override fun areContentsTheSame(oldItem: Clipboard, newItem: Clipboard): Boolean {
                return oldItem == newItem
            }
        }
    }
}