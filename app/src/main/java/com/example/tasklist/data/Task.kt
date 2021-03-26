package com.example.tasklist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_table")
data class Task (

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val task: String,
    val description: String
): Parcelable