package com.cupcake.todo.ui.fragment.team_tasks

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentTeamTasksBinding
import com.cupcake.todo.presenter.teamtasks.TeamTasksPresenter
import com.cupcake.todo.ui.base.BaseFragment

class TeamTasksFragment : BaseFragment<FragmentTeamTasksBinding>(),ITeamTasksView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentTeamTasksBinding
        get() = FragmentTeamTasksBinding::inflate
    private lateinit var presenter: TeamTasksPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = TeamTasksPresenter(this)
        presenter.teamTasks()
    }

    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "showLoading")    }

    override fun onTeamTasksSuccess() {
        Log.v(LOG_TAG, "showLoading")    }

    override fun onTeamTasksFailure(error: String) {
        Log.v(LOG_TAG, "onRegisterFailure $error")
    }
}

