package com.example.mygoodnotes.mainModule.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mygoodnotes.common.utils.OnClickListener
import com.example.mygoodnotes.R
import com.example.mygoodnotes.common.entities.NoteEntity
import com.example.mygoodnotes.databinding.ItemNotesBinding


class NoteListAdapter(private val listener: OnClickListener): ListAdapter<NoteEntity, RecyclerView.ViewHolder>(
    noteDiffCallBack()
) {

    private lateinit var mContext: Context

    inner class viewHolder(view: View): RecyclerView.ViewHolder(view){

        val binding = ItemNotesBinding.bind(view)

        fun setListener(noteEntity: NoteEntity){
            binding.root.setOnClickListener { listener.onClick(noteEntity) }
            binding.root.setOnLongClickListener {
                listener.onDelete(noteEntity)
                true
            }
        }


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_notes, parent, false)
        return viewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val note = getItem(position)

        with(holder as viewHolder){
            setListener(note)
            binding.tvCardTitle.text = note.name
            binding.tvDescription.text = note.description
            binding.headerLayout.setBackgroundColor(note.color)
            if (note.isReminder) binding.icClock.visibility = View.VISIBLE

        }
    }

    class noteDiffCallBack: DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

    }

}