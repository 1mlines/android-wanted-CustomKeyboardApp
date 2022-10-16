package com.preonboarding.customkeyboard.presentation.clipboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.presentation.databinding.ItemClipboardBinding

class ClipboardAdapter(
    private val onItemClick: (Bookmark) -> Unit,
    private val onDeleteClick: (Bookmark) -> Unit,
) : ListAdapter<Bookmark, ClipboardAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemClipboardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let{
            holder.bind(bookmark = it)
        }
    }

    inner class ViewHolder(private val binding: ItemClipboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(bookmark: Bookmark) {
                binding.bookmark = bookmark

                binding.layoutItemBookmark.setOnClickListener {
                    onItemClick.invoke(bookmark)
                }

                binding.ivItemBookmarkDeleteBtn.setOnClickListener {
                    onDeleteClick.invoke(bookmark)
                }
            }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Bookmark>() {
            override fun areItemsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Bookmark, newItem: Bookmark): Boolean {
                return oldItem == newItem
            }
        }
    }

}