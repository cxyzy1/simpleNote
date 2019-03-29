package com.cxyzy.note.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cxyzy.note.R
import com.cxyzy.note.db.bean.Note
import kotlinx.android.synthetic.main.item_task.view.*

class NoteAdapter : PagedListAdapter<Note, NoteAdapter.ViewHolder>(DiffCallback()) {
    private lateinit var onItemClick: (note: Note) -> Unit
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position) ?: return
        holder.itemView.noteTv.text = data.name
        holder.itemView.setOnClickListener { onItemClick(data) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    infix fun setOnItemClick(onClick: (note: Note) -> Unit) {
        this.onItemClick = onClick
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!)
}

private class DiffCallback : DiffUtil.ItemCallback<Note>() {
    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}