package com.cupcake.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentTasksTeamBinding

class TasksTeamFragment : BaseFragment<FragmentTasksTeamBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentTasksTeamBinding
        get() = FragmentTasksTeamBinding::inflate
}

