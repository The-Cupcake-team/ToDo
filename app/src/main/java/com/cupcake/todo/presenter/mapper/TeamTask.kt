package com.cupcake.todo.presenter.mapper

import com.cupcake.todo.model.network.response.TeamTaskResponse
import com.cupcake.todo.presenter.model.TeamTask

fun TeamTaskResponse.toTeamTask(): TeamTask {
    return TeamTask(
        id= id,
        title= title,
        description= description,
        assignee= assignee,
        status= status,
        creationTime= creationTime
    )
}
