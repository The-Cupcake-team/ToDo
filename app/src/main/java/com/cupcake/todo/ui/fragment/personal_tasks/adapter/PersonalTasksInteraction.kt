package com.cupcake.todo.ui.fragment.personal_tasks.adapter

import com.cupcake.todo.data.network.response.PersonalTask

interface PersonalTasksInteraction {
    fun onClickPersonalTask(personalTask: PersonalTask)
}