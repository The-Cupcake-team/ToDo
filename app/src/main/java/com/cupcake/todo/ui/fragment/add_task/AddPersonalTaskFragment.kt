package com.cupcake.todo.ui.fragment.add_task

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentAddTaskBinding
import com.cupcake.todo.presenter.add_personal_task.AddPersonalTaskPresenter
import com.cupcake.todo.ui.base.BaseFragment

class AddPersonalTaskFragment : BaseFragment<FragmentAddTaskBinding>(), IAddPersonalTask {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentAddTaskBinding =
        FragmentAddTaskBinding::inflate
    private lateinit var presenter: AddPersonalTaskPresenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hiddenAssignNoNeedInPersonalTask()
        presenter = AddPersonalTaskPresenter(this)
        onClickAddPersonalTask()
    }

    private fun onClickAddPersonalTask() {
        binding.buttonAddTask.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            presenter.addPersonalTask(title, description)
        }
    }

    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "hideLoading")
    }

    override fun onSuccessAdded() {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), R.string.added_successful, Toast.LENGTH_SHORT).show()
        }

    }

    override fun onFailureAdded(error: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireContext(), R.string.added_failed, Toast.LENGTH_SHORT).show()
        }

    }

    private fun hiddenAssignNoNeedInPersonalTask() {
        binding.textViewAssign.visibility = View.GONE
        binding.recyclerViewProfile.visibility = View.GONE
    }

}