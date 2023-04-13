package com.cupcake.todo.ui.fragment.add_team_task

import com.cupcake.todo.ui.fragment.register.IView

interface IAddTeamTaskView: IView {
    fun onAddedTeamTaskSuccess(result: String)
    fun onAddedTeamTaskFailed(error: String)
}