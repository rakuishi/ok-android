package com.rakuishi.ok.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rakuishi.ok.R
import com.rakuishi.ok.data.Gist
import com.rakuishi.ok.data.Repo
import kotlinx.android.synthetic.main.list_item_two_line.view.*

class TwoLineViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_two_line, parent, false)
    ) {

    fun bind(repo: Repo, onItemClick: ((Repo) -> Unit)) {
        itemView.primaryTextView.text = repo.name
        itemView.secondaryTextView.text = repo.description
        itemView.backgroundLayout.setOnClickListener { onItemClick.invoke(repo) }
    }

    fun bind(gist: Gist, onItemClick: ((Gist) -> Unit)) {
        itemView.primaryTextView.text = gist.id
        itemView.secondaryTextView.text = gist.updatedAt
        itemView.backgroundLayout.setOnClickListener { onItemClick.invoke(gist) }
    }
}