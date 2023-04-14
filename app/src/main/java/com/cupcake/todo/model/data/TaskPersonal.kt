package com.cupcake.todo.model.data

class TaskPersonal(
    id: String,
    title: String,
    description: String,
    status: String,
    createTime: String
) : Task(id, title, description, status, createTime)