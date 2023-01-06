package com.example.todolist.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.model.Task

interface RepositoryTask {

    suspend fun addTask(task: Task)

    suspend fun getTasks(): LiveData<List<Task>>

    suspend fun getTask(id: String): Task

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun deleteTasks()

    suspend fun search(textSearch: String): LiveData<List<Task>>
}