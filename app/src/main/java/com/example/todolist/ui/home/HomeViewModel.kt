package com.example.todolist.ui.home

import androidx.lifecycle.*
import com.example.todolist.data.model.Task
import com.example.todolist.data.repository.RepositoryTask
import com.example.todolist.utils.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(val repositoryTask: RepositoryTask) : BaseViewModel() {

//    private val _tasks = MutableLiveData<List<Task>>()
//    val tasks: LiveData<List<Task>> = _tasks


    fun addTask(task: Task) {
        viewModelScope.launch {
            repositoryTask.addTask(task)
        }
    }
    val getTasks : LiveData<List<Task>> = liveData {
        emitSource(repositoryTask.getTasks())
    }

//    fun getTasks() {
//
//        viewModelScope.launch {
//            _tasks.value = repositoryTask.getTasks()
//        }
//    }

//    fun getTask(id: String): Task {
//        return repositoryTask.getTask(id)
//    }

    fun deleteAllTask() {
        viewModelScope.launch {
            repositoryTask.deleteTasks()
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            repositoryTask.deleteTask(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch {
            repositoryTask.updateTask(task)
        }
    }

    fun searchTask(textSearch: String):LiveData<List<Task>> = liveData {
        emitSource(repositoryTask.search(textSearch))
    }

//    fun searchTask(textSearch: String) {
//        viewModelScope.launch {
//           tasks = repositoryTask.search(textSearch)
//        }
//    }
}