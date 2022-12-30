package com.example.todolist.data.repository

import com.example.todolist.data.db.TaskDao
import com.example.todolist.data.model.Task

class RepositoryTaskImpl(val dao: TaskDao):RepositoryTask {
    override fun addTask(task: Task) {
        dao.addTask(task)
    }

    override fun getTasks(): List<Task> = dao.getTasks()

    override fun getTask(id: String): Task = dao.getTask(id)

    override fun updateTask(task: Task) =dao.updateTask(task)

    override fun deleteTask(task: Task) = dao.deleteTask(task)

    override fun deleteTasks() {
        dao.deleteAllTask()
    }
}