package com.example.todolist.data.repository

import androidx.lifecycle.LiveData
import com.example.todolist.data.db.TaskDao
import com.example.todolist.data.model.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class RepositoryTaskImpl(val dao: TaskDao) : RepositoryTask {
    override suspend fun addTask(task: Task) {
        dao.addTask(task)
    }

    override suspend fun getTasks(): Flow<List<Task>> {
        return dao.getTasks()
    }

    override suspend fun getTask(id: String): Task = dao.getTask(id)

    override suspend fun updateTask(task: Task) = dao.updateTask(task)

    override suspend fun deleteTask(task: Task) = dao.deleteTask(task)

    override suspend fun deleteTasks() {
        dao.deleteAllTask()
    }

    override suspend fun search(textSearch: String): Flow<List<Task>> = dao.searchTask(textSearch)

}