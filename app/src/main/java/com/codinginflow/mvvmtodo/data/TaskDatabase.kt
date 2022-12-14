package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.codinginflow.mvvmtodo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities =[Task::class], version = 1 )
abstract class TaskDatabase:RoomDatabase() {
    abstract fun taskDao():TaskDao

    class CallBack @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            //db operations
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the short", important = true))
                dao.insert(Task("Learn the mvvm"))
                dao.insert(Task("Laravel Project", important = true))
                dao.insert(Task("Building An Application"))
                dao.insert(Task("Testing Webserver"))
            }

        }
    }
}