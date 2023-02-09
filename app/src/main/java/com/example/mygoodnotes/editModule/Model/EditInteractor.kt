package com.example.mygoodnotes.editModule.Model

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import com.example.mygoodnotes.NoteApplication
import com.example.mygoodnotes.R
import com.example.mygoodnotes.common.database.NoteDatabase
import com.example.mygoodnotes.common.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditInteractor {

    suspend fun addNote(noteEntity: NoteEntity) = withContext(Dispatchers.IO){
        val result = NoteApplication.database.NoteDao().addNote(noteEntity)
        if(result == 0L) { throw Exception ("Add Error") }
        println(result)
    }

    suspend fun updateNote(noteEntity: NoteEntity) = withContext(Dispatchers.IO){
        val result = NoteApplication.database.NoteDao().updateNote(noteEntity)
        if(result == 0 ) throw Exception("Update error")
    }

    fun getNoteById(id: Long): LiveData<NoteEntity>{
        return NoteApplication.database.NoteDao().getNoteById(id)
    }

}