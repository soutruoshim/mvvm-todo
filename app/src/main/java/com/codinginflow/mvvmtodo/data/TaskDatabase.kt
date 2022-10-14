package com.codinginflow.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities =[Task::class], version = 1 )
abstract class TaskDatabase:RoomDatabase() {
    abstract fun taskDao():TaskDao

    class CallBack @Inject constructor(
        private val database: Provider<TaskDatabase>,
        private val applicationScope: CoroutineScope
    ):RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            //db operations
            val dao = database.get().taskDao()

            applicationScope.launch {
                dao.insert(Task("Wash the dishe", important = true))
                dao.insert(Task("Wash the dishe"))
                dao.insert(Task("Wash the dishe", important = true))
                dao.insert(Task("Wash the dishe"))
                dao.insert(Task("Wash the dishe"))
            }

        }
    }
}