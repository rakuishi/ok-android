package com.rakuishi.ok.presentation.gist

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakuishi.ok.data.Gist
import com.rakuishi.ok.presentation.common.TwoLineViewHolder

class GistAdapter : ListAdapter<Gist, RecyclerView.ViewHolder>(GistDiffCallback()) {

    var onItemClick: ((Gist) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TwoLineViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TwoLineViewHolder).bind(getItem(position)) {
            onItemClick?.invoke(it)
        }
    }
}

private class GistDiffCallback : DiffUtil.ItemCallback<Gist>() {
    override fun areItemsTheSame(oldItem: Gist, newItem: Gist): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gist, newItem: Gist): Boolean {
        return oldItem == newItem
    }
}