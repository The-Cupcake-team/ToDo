package com.cupcake.todo.ui.fragment.personal_tasks.model

import kotlinx.android.parcel.Parcelize

@Parcelize
class PersonalTask(
    override val id: String,
    override val title: String,
    override val description: String,
    override val status: Int,
    override val creationTime: String
) : Task(id, title, description, status, creationTime)