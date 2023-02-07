package com.example.mygoodnotes.common.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mygoodnotes.common.entities.NoteEntity

@Database(entities = arrayOf(NoteEntity::class), version = 1)
abstract class NoteDatabase: RoomDatabase (){
    abstract fun NoteDao(): NoteDao

}