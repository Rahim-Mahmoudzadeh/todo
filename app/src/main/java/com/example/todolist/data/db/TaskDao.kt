package com.example.todolist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.todolist.data.model.Task

@Dao
interface TaskDao {

    @Insert()
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM tbl_task")
    fun getTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM tbl_task WHERE id =:id")
    suspend fun getTask(id: String): Task

    @Query("SELECT * FROM tbl_task WHERE name LIKE '%' || :textSearch || '%'")
    fun searchTask(textSearch: String): LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM tbl_task")
    suspend fun deleteAllTask()
}