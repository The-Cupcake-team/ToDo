package com.cupcake.todo.ui.fragment.team_tasks.adapter

import com.cupcake.todo.data.network.response.TeamTask


interface TeamTasksInteractionListener {
    fun onClickTeamTask(teamTask: TeamTask)
}