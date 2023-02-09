package com.example.mygoodnotes.common.utils

import com.example.mygoodnotes.common.entities.NoteEntity

interface OnClickListener {
    fun onClick(noteEntity: NoteEntity)

    fun onDelete(noteEntity: NoteEntity)

}