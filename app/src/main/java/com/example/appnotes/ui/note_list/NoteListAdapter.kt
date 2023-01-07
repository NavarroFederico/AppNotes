package com.example.appnotes.ui.note_list

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appnotes.domain.model.Note
import com.example.apppositive.R
import com.example.apppositive.databinding.LayoutNoteItemBinding

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutNoteItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }

    inner class NoteViewHolder(itemBinding: LayoutNoteItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        private val container = itemBinding.noteContainer
        private val title = itemBinding.textViewNoteTitle
        private val content = itemBinding.textViewNoteContent
        private val backgroundColor = container.background as GradientDrawable
        private val strokeColor = container.background as GradientDrawable

        fun bind(note: Note) {
            backgroundColor.color = ColorStateList.valueOf(ContextCompat.getColor(itemView.context, note.color))
            if( note.color == R.color.app_bg_color){
                strokeColor.setStroke(2,ContextCompat.getColor(itemView.context,R.color.on_secondary_color))
            }else{
                strokeColor.setStroke(2,ContextCompat.getColor(itemView.context,note.color))
            }
            if(note.title.isNotEmpty()){
                title.isVisible = true
                title.text = note.title
            }

            if(note.content.isNotEmpty()){
                content.isVisible = true
                content.text = note.content
            }
        }

    }

}

object NoteDiffUtil : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }

}
