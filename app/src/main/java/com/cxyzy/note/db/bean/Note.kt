package com.cxyzy.note.db.bean

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(@PrimaryKey(autoGenerate = true) val id: Int,
                var name: String)