package com.example.todolist.data.repository

import com.example.todolist.data.model.Task

interface RepositoryTask {

    fun addTask(task: Task)

    fun getTasks(): List<Task>

    fun getTask(id: String): Task

    fun updateTask(task: Task)

    fun deleteTask(task: Task)

    fun deleteTasks()
}