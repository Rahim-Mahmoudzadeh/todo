package com.example.todolist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_task")
data class Task(
    val name: String,
    val status: Int,
    var isChecked: Boolean=false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}