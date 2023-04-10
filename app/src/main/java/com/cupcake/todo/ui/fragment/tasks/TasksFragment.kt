package com.cupcake.todo.ui.fragment.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentTasksBinding
import com.cupcake.todo.ui.base.BaseFragment


class TasksFragment : BaseFragment<FragmentTasksBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentTasksBinding
        get() = FragmentTasksBinding::inflate
}



