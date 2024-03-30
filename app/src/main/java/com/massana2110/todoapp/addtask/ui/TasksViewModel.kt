package com.massana2110.todoapp.addtask.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(

): ViewModel() {

    fun onTaskCreated(task: String) {
        println("Task: $task")
    }

}