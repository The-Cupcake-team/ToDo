package com.cupcake.todo.ui.fragment.team_tasks.adapter

import com.cupcake.todo.ui.fragment.personal_tasks.model.TeamTask


interface TeamTasksInteractionListener {
    fun onClickTeamTask(teamTask: TeamTask)
}