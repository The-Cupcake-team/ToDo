package com.cupcake.todo.ui.fragment.team_tasks

import com.cupcake.todo.model.network.response.TeamTasksResponse

data class TeamTaskData(
    val id: String,
    val title: String,
    val description: String,
    val assignee: String,
    val status: Int,
    val creationTime: String
)
fun mapTeamTasksResponseToData(response: TeamTasksResponse): TeamTaskData {
    return TeamTaskData(
        response.id,
        response.title,
        response.description,
        response.assignee,
        response.status,
        response.creationTime
    )
}
