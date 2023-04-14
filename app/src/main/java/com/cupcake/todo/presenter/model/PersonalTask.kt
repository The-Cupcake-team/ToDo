package com.cupcake.todo.presenter.model

class PersonalTask(
    override val id: String,
    override val title: String,
    override val description: String,
    override val status: Int,
    override val creationTime: String
) : Task(id, title, description, status, creationTime)