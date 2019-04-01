package com.cxyzy.note.db.bean

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity
data class Note(@PrimaryKey(autoGenerate = true) val id: Long,
                var content: String,
                var updateDate: Date) : Parcelable