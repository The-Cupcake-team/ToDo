package com.cupcake.todo.ui.fragment.team_tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentTeamTasksBinding
import com.cupcake.todo.ui.base.BaseFragment

class TeamTasksFragment : BaseFragment<FragmentTeamTasksBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentTeamTasksBinding
        get() = FragmentTeamTasksBinding::inflate
}

