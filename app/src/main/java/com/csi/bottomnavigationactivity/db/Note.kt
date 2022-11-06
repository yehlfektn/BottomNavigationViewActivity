package com.csi.bottomnavigationactivity.db

import androidx.room.Entity
import androidx.room.PrimaryKey

// on below line we are specifying our table name
@Entity(tableName = "notesTable")
// on below line we are specifying our column info
// and inside that we are passing our column name
class Note(
    val noteTitle: String,
    val noteDescription: String,
    val timeStamp: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)