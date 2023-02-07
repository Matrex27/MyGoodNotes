package com.example.mygoodnotes.mainModule.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mygoodnotes.common.entities.NoteEntity
import com.example.mygoodnotes.mainModule.Model.MainInteractor

class MainViewModel: ViewModel() {

    private val interactor = MainInteractor()

    private val notes = interactor.notes


    fun getNotes(): LiveData<MutableList<NoteEntity>>{
        return notes
    }



}