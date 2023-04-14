package com.cupcake.todo.ui.util

import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.fragment.home.HomeItem
import com.cupcake.todo.ui.fragment.home.HomeItemType


fun TeamTask.toTeamTask(): HomeItem<Any> {
    return HomeItem(this, HomeItemType.ITEM_TYPE_TEAM_TASK)
}

fun PersonalTask.toPersonalTask(): HomeItem<Any> {
    return HomeItem(this, HomeItemType.ITEM_TYPE_PERSONAL_TASK)
}
