package com.cxyzy.note.db.bean

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Note(@PrimaryKey(autoGenerate = true) val id: Int,
                var name: String): Parcelable