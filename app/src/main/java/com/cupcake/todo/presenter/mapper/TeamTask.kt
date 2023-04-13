package com.cupcake.todo.presenter.mapper

import com.cupcake.todo.model.network.response.TeamTasksResponse
import com.cupcake.todo.presenter.model.TeamTaskData

fun TeamTasksResponse.toTeamTask(): TeamTaskData {
    return TeamTaskData(
        id= id,
        title= title,
        description= description,
        assignee= assignee,
        status= status,
        creationTime= creationTime
    )
}
