package com.example.todolist.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.model.Task

@Dao
interface TaskDao {

    @Insert()
    fun addTask(task: Task)

    @Query("SELECT * FROM tbl_task")
    fun getTasks():List<Task>

    @Query("SELECT * FROM tbl_task WHERE id =:id")
    fun getTask(id: String): Task

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM tbl_task")
    fun deleteAllTask()
}