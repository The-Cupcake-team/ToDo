package com.cupcake.todo.presenter.mapper

import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.presenter.model.TeamTaskData

fun TeamTask.toTeamTask(): TeamTaskData {
    return TeamTaskData(
        id= id,
        title= title,
        description= description,
        assignee= assignee,
        status= status,
        creationTime= creationTime
    )
}
