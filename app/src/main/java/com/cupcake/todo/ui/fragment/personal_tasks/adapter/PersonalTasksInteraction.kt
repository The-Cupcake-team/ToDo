package com.cupcake.todo.ui.fragment.personal_tasks.adapter

import com.cupcake.todo.model.network.response.PersonalTask

interface PersonalTasksInteraction {
    fun onClickPersonalTask(personalTask: PersonalTask)
}