package com.example.todolist.ui.home

import com.example.todolist.data.model.Task
import com.example.todolist.data.repository.RepositoryTask
import com.example.todolist.utils.BaseViewModel

class HomeViewModel(val repositoryTask: RepositoryTask) : BaseViewModel() {
    fun addTask(task: Task) {
        repositoryTask.addTask(task)
    }

    fun getTasks(): List<Task> {
        return repositoryTask.getTasks()
    }

    fun getTask(id: String): Task {
        return repositoryTask.getTask(id)
    }

    fun deleteAllTask() {
        repositoryTask.deleteTasks()
    }

    fun deleteTask(task: Task) {
        repositoryTask.deleteTask(task)
    }

    fun update(task: Task) {
        repositoryTask.updateTask(task)
    }

    fun searchTask(textSearch: String):List<Task> {
        return repositoryTask.search(textSearch)
    }
}