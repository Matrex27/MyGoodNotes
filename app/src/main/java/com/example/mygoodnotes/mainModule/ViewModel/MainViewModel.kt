package com.example.mygoodnotes.mainModule.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mygoodnotes.common.entities.NoteEntity
import com.example.mygoodnotes.mainModule.Model.MainInteractor
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val interactor = MainInteractor()

    private val notes = interactor.notes


    fun getNotes(): LiveData<MutableList<NoteEntity>>{
        return notes
    }

    fun deleteNote(noteEntity: NoteEntity){
        viewModelScope.launch {
            try {
                interactor.deleteNote(noteEntity)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }

    }


}