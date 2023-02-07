package com.example.mygoodnotes.editModule.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygoodnotes.common.entities.NoteEntity
import com.example.mygoodnotes.editModule.Model.EditInteractor
import kotlinx.coroutines.launch

class EditViewModel: ViewModel() {

    private val interactor: EditInteractor

    init {
        interactor = EditInteractor()
    }

    private var noteSelected = MutableLiveData<NoteEntity>()



    fun setNoteSelected(noteEntity: NoteEntity){
        noteSelected.value = noteEntity
    }

    fun getNoteSelected():LiveData<NoteEntity>{
        return noteSelected
    }


    fun addNote(noteEntity: NoteEntity){
        viewModelScope.launch {
            try {
                interactor.addNote(noteEntity)
            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

}