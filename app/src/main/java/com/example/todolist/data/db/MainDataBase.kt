package com.example.todolist.data.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.data.model.Task
import com.example.todolist.utils.Constant.DATABASE_NAME

@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class MainDataBase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}