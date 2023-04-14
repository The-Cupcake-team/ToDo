package com.cupcake.todo.ui.fragment.add_team_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentAddTaskBinding
import com.cupcake.todo.presenter.addTeamTask.AddTeamTaskPresenter
import com.cupcake.todo.ui.base.BaseFragment

class AddTeamTaskFragment : BaseFragment<FragmentAddTaskBinding>(),
    IAddTeamTaskView,
    AssigneeRecyclerAdapter.IAssigneeClickListener {
    override val LOG_TAG: String = "testttttttt"
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentAddTaskBinding =
        FragmentAddTaskBinding::inflate

    private lateinit var presenter: AddTeamTaskPresenter
    private var assignee = ""
    private var title = ""
    private var description = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = AddTeamTaskPresenter(this)
        val adapter = AssigneeRecyclerAdapter(this, presenter.assignee)
        binding.recyclerViewProfile.adapter = adapter
        binding.buttonAddTask.setOnClickListener {
            title = binding.editTextTitle.text.toString()
            description = binding.editTextDescription.text.toString()
            checkNotEmptyFields()
        }
        setupBackButton()
    }

    private fun checkNotEmptyFields() {
        if (title.isNotBlank() && description.isNotBlank() && assignee.isNotBlank()) {
            presenter.addTeamTask(title, description, assignee)
        } else {
            validateInput()
        }
    }

    private fun validateInput() {
        val titleInput = binding.editTextTitle
        val descriptionInput = binding.editTextDescription
        if (title.isBlank()) {
            titleInput.error = getString(R.string.error_title_message)
            titleInput.requestFocus()
        }
        if (description.isBlank()) {
            descriptionInput.error = getString(R.string.error_descrption_message)
            descriptionInput.requestFocus()
        }
        if (assignee.isBlank()) {
            Toast.makeText(requireActivity(), R.string.assignee_not_selected, Toast.LENGTH_SHORT).show()
        }
    }
    override fun onAddedTeamTaskSuccess(result: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireActivity(), R.string.added_successful, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBackButton() {
        binding.toolBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onAddedTeamTaskFailed(error: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireActivity(), R.string.added_failed, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAssigneeItemClicked(assignee: String) {
        this.assignee = assignee
    }

}
