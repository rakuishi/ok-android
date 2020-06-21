package com.rakuishi.ok.presentation.feed

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakuishi.ok.data.Feed
import com.rakuishi.ok.presentation.common.TwoLineViewHolder

class FeedAdapter : ListAdapter<Feed, RecyclerView.ViewHolder>(FeedDiffCallback()) {

    var onItemClick: ((Feed) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TwoLineViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TwoLineViewHolder).bind(getItem(position)) {
            onItemClick?.invoke(it)
        }
    }
}

private class FeedDiffCallback : DiffUtil.ItemCallback<Feed>() {
    override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem.link == newItem.link
    }

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return oldItem == newItem
    }
}