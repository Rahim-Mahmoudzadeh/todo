package com.example.todolist.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tbl_task")
@Parcelize
data class Task(
    val name: String,
    val status: Int,
    var isChecked: Boolean = false
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}