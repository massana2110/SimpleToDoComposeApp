package com.massana2110.todoapp.addtask.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = { TasksTopAppBar() },
        floatingActionButton = { MyFab() { showDialog = true } }
    ) {
        AddTaskDialog(
            show = showDialog,
            onDismiss = { showDialog = false },
            onTaskAdded = {
                tasksViewModel.onTaskCreated(it)
                showDialog = false
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksTopAppBar() {
    TopAppBar(
        title = { Text(text = "ToDo App") }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Composable
fun MyFab(onClickFab: () -> Unit) {
    FloatingActionButton(onClick = { onClickFab() }) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
    }
}

@Composable
fun AddTaskDialog(show: Boolean, onDismiss: () -> Unit, onTaskAdded: (String) -> Unit) {
    var myTask by remember {
        mutableStateOf("")
    }

    if (show) {
        Dialog(onDismissRequest = onDismiss) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = "Add task")
                Spacer(modifier = Modifier.size(16.dp))
                OutlinedTextField(
                    value = myTask,
                    onValueChange = { myTask = it },
                    Modifier.fillMaxWidth(),
                    singleLine = true,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = { onTaskAdded(myTask) }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Add")
                }
            }
        }
    }
}

@Preview(device = "spec:width=1080px,height=2400px", showSystemUi = true)
@Composable
private fun TasksScreenPreview() {
    TasksScreen(TasksViewModel())
}

@Preview(name = "TaskDialog")
@Composable
private fun AddTaskDialogPreview() {
    AddTaskDialog(show = true, onDismiss = {}, onTaskAdded = {})
}