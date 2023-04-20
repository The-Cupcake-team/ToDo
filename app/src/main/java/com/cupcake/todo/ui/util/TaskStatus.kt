package com.cupcake.todo.ui.util

sealed class TaskStatus(val state: Int) {
    object ToDo : TaskStatus(0)
    object InProgress : TaskStatus(1)
    object Done : TaskStatus(2)
    object All : TaskStatus(3)
}
