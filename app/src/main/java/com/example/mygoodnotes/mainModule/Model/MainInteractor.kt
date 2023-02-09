package com.example.mygoodnotes.mainModule.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.mygoodnotes.NoteApplication
import com.example.mygoodnotes.common.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainInteractor {

    var notes: LiveData<MutableList<NoteEntity>> = liveData {
        val notesLiveData = NoteApplication.database.NoteDao().getAllNotes()

        emitSource(notesLiveData.map {
            it.sortedBy { it.id }.toMutableList()})
    }

    suspend fun deleteNote(noteEntity: NoteEntity)= withContext(Dispatchers.IO){
        val result = NoteApplication.database.NoteDao().deleteNote(noteEntity)
        if (result == 0 ) throw Exception("Delete error")
    }


}