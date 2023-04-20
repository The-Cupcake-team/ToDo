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
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddTaskBinding =
        FragmentAddTaskBinding::inflate
    private lateinit var presenter: AddPersonalTaskPresenter
    private lateinit var title: String
    private lateinit var description: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        hiddenAssignNoNeedInPersonalTask()
        presenter = AddPersonalTaskPresenter(this)
        onClickAddPersonalTask()
        setupBackButton()
    }

    private fun onClickAddPersonalTask() {
        binding.buttonAddTask.setOnClickListener {
            title = binding.editTextTitle.text.toString().trimEnd()
            description = binding.editTextDescription.text.toString().trimEnd()
            checkInputValue()
        }
    }

    private fun checkInputValue() {
        if (title.isNotBlank() && description.isNotBlank()) {
            presenter.addPersonalTask(title, description)
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
    }

    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "hideLoading")
    }

    override fun onSuccessAdded() {
        requireActivity().runOnUiThread {
            Toast.makeText(requireActivity(), R.string.added_successful, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailureAdded(error: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireActivity(), R.string.added_failed, Toast.LENGTH_SHORT).show()
        }
    }

    private fun hiddenAssignNoNeedInPersonalTask() {
        binding.textViewAssign.visibility = View.GONE
        binding.recyclerViewProfile.visibility = View.GONE
    }
    private fun setupBackButton() {
        binding.toolBar.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}