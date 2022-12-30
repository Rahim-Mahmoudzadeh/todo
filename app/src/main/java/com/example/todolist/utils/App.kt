package com.example.todolist.utils

import android.app.Application
import androidx.room.Room
import com.example.todolist.BuildConfig
import com.example.todolist.data.db.MainDataBase
import com.example.todolist.data.db.TaskDao
import com.example.todolist.data.repository.RepositoryTask
import com.example.todolist.data.repository.RepositoryTaskImpl
import com.example.todolist.ui.home.HomeViewModel
import com.example.todolist.utils.Constant.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG)
        {
            Timber.plant()
        }

        startKoin {
            androidContext(this@App)
            modules(main)
        }
    }

    val main = module {
        single {
            Room.databaseBuilder(
                this@App,
                MainDataBase::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries().build()
        }
        single {
            val database = get<MainDataBase>()
            database.taskDao()
        }
        factory<RepositoryTask> { RepositoryTaskImpl(get()) }
        viewModel { HomeViewModel(get()) }
    }

}