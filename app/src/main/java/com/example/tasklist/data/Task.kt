package com.example.tasklist.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Task (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    val description: String,
    val date: String
): Parcelable



