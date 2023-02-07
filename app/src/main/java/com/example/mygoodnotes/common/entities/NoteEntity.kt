package com.example.mygoodnotes.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NoteEntity (@PrimaryKey(autoGenerate = true) var id: Long = 0,
                  var name: String,
                  var description: String,
                  var color: Int = 0,
                  var isReminder: Boolean = false,
                  var date: String = "",
                  var hour: String = ""
) {

    constructor(): this(name = "", description = "")
}