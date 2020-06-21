package com.rakuishi.ok.presentation.repo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rakuishi.ok.R
import com.rakuishi.ok.data.Repo
import kotlinx.android.synthetic.main.list_item_repo.view.*

class RepoAdapter : ListAdapter<Repo, RecyclerView.ViewHolder>(RepoDiffCallback()) {

    var onItemClick: ((Repo) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RepoViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepoViewHolder).bind(getItem(position)) {
            onItemClick?.invoke(it)
        }
    }
}

private class RepoViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_repo, parent, false)
    ) {

    fun bind(repo: Repo, onItemClick: ((Repo) -> Unit)) {
        itemView.primaryTextView.text = repo.name
        itemView.secondaryTextView.text = repo.description
        itemView.backgroundLayout.setOnClickListener { onItemClick.invoke(repo) }
    }
}

private class RepoDiffCallback : DiffUtil.ItemCallback<Repo>() {
    override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
        return oldItem == newItem
    }
}