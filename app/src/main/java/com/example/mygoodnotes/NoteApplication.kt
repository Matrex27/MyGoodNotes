package com.example.mygoodnotes

import android.app.Application
import androidx.room.Room
import com.example.mygoodnotes.common.database.NoteDatabase

class NoteApplication: Application() {
    companion object{
        lateinit var database: NoteDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, NoteDatabase::class.java, "NoteEntity").build()
    }
}