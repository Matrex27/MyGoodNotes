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

    private var noteId : Long = 0

    private var noteSelected = MutableLiveData<NoteEntity>()
    private var result = MutableLiveData<Any>()



    fun setNoteSelected(noteEntity: NoteEntity){
        noteId = noteEntity.id
    }

    fun getNoteSelected():LiveData<NoteEntity>{
        return interactor.getNoteById(noteId)
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

    fun updateNote(noteEntity: NoteEntity){
        viewModelScope.launch {
            try {
                interactor.updateNote(noteEntity)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}