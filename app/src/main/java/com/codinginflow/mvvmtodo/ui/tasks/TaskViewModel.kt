package com.codinginflow.mvvmtodo.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.codinginflow.mvvmtodo.data.TaskDao

class TaskViewModel @ViewModelInject constructor(
    private var taskDao:TaskDao
):ViewModel() {
    val tasks = taskDao.getTasks().asLiveData()
}