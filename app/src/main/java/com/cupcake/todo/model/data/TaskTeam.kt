package com.cupcake.todo.model.data

class TaskTeam(
    id: String,
    title: String,
    description: String,
    status: Int,
    createTime: String,
    assignee: String
) : Task(id, title, description, status, createTime)