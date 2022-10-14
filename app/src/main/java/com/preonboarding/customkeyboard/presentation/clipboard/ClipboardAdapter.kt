package com.preonboarding.customkeyboard.presentation.clipboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.preonboarding.customkeyboard.databinding.ItemClipboardBinding
import com.preonboarding.customkeyboard.domain.model.Clipboard

class ClipboardAdapter(
    private val onItemDeleted: (Int) -> Unit
) : ListAdapter<Clipboard, ClipboardAdapter.ClipboardViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipboardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemClipboardBinding.inflate(inflater, parent, false)
        return ClipboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClipboardViewHolder, position: Int) {
        holder.bindItems(getItem(position), onItemDeleted)
    }

    class ClipboardViewHolder(private val binding: ItemClipboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItems(item: Clipboard, onItemDeleted: (Int) -> Unit) {
            binding.tvClipData.text = item.clipData
            binding.ivDelete.setOnClickListener {
                onItemDeleted.invoke(item.id)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Clipboard>() {
            override fun areItemsTheSame(oldItem: Clipboard, newItem: Clipboard): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Clipboard, newItem: Clipboard): Boolean {
                return oldItem == newItem
            }
        }
    }
}