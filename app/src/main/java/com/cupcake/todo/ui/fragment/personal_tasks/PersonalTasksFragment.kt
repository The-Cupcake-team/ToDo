package com.cupcake.todo.ui.fragment.personal_tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentPersonalTasksBinding
import com.cupcake.todo.ui.base.BaseFragment

class PersonalTasksFragment : BaseFragment<FragmentPersonalTasksBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentPersonalTasksBinding
        get() = FragmentPersonalTasksBinding::inflate
}