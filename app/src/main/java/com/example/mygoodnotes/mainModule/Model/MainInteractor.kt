package com.example.mygoodnotes.mainModule.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.mygoodnotes.NoteApplication
import com.example.mygoodnotes.common.entities.NoteEntity

class MainInteractor {

    var notes: LiveData<MutableList<NoteEntity>> = liveData {
        val notesLiveData = NoteApplication.database.NoteDao().getAllNotes()

        emitSource(notesLiveData.map {
            it.sortedBy { it.id }.toMutableList()})
    }


}