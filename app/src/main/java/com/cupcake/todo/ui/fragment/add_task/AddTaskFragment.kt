package com.cupcake.todo.ui.fragment.add_task

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentAddTaskBinding
import com.cupcake.todo.ui.base.BaseFragment

class AddTaskFragment : BaseFragment<FragmentAddTaskBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddTaskBinding =
        FragmentAddTaskBinding::inflate

}