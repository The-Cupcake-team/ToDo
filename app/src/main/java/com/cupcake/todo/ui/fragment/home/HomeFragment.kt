package com.cupcake.todo.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentHomeBinding
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.base.BaseFragment


class HomeFragment() : BaseFragment<FragmentHomeBinding>(), IHomeView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onGetDataSuccess() {
        TODO("Not yet implemented")
    }

    override fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>) {
        TODO("Not yet implemented")
    }


    override fun onToDoTeamTasksSuccess(teamTasks: List<TeamTask>) {
        TODO("Not yet implemented")
    }

    override fun onInProgressTeamTasksSuccess(teamTasks: List<TeamTask>) {
        TODO("Not yet implemented")
    }

    override fun onDoneTeamTasksSuccess(teamTasks: List<TeamTask>) {
        TODO("Not yet implemented")
    }

    override fun onGetDataFailure(error: String) {
        TODO("Not yet implemented")
    }


}
