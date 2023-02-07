package com.example.mygoodnotes.editModule.Model

import com.example.mygoodnotes.NoteApplication
import com.example.mygoodnotes.common.database.NoteDatabase
import com.example.mygoodnotes.common.entities.NoteEntity

class EditInteractor {

    suspend fun addNote(noteEntity: NoteEntity){
        NoteApplication.database.NoteDao().addNote(noteEntity)
    }

    suspend fun updateNote(noteEntity: NoteEntity){
        val result = NoteApplication.database.NoteDao().updateNote(noteEntity)
    }

}