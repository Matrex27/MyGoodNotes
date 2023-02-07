package com.example.mygoodnotes.common.database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mygoodnotes.common.entities.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM NoteEntity")
    fun getAllNotes(): LiveData<List<NoteEntity>>

    @Query("SELECT * FROM NoteEntity where id = :id")
    fun getNoteById(id: Long): LiveData<NoteEntity>

    @Insert
    suspend fun addNote(noteEntity: NoteEntity): Long

    @Update
    suspend fun updateNote(noteEntity: NoteEntity): Int

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity): Int

}