package com.hugh.presentation.ui.keyboard.clipBoard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hugh.model.ClipBoardData
import com.hugh.presentation.R
import com.hugh.presentation.action.clipAction.ClipBoardActor

/**
 * Created by 서강휘
 */


class ClipAdapter(private val clipBoardActor: ClipBoardActor) :
    ListAdapter<ClipBoardData, ClipViewHolder>(object : DiffUtil.ItemCallback<ClipBoardData>() {
        override fun areItemsTheSame(oldItem: ClipBoardData, newItem: ClipBoardData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClipBoardData, newItem: ClipBoardData): Boolean {
            return oldItem == newItem
        }

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipViewHolder {
        return ClipViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.clip_item,
                parent,
                false
            ),
            clipBoardActor
        )
    }

    override fun onBindViewHolder(holder: ClipViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}