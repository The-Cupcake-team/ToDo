package com.cupcake.todo.ui.fragment.details


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isGone
import com.cupcake.todo.databinding.FragmentDetailsBinding
import com.cupcake.todo.model.data.Task
import com.cupcake.todo.model.data.TaskPersonal
import com.cupcake.todo.presenter.details.DetailsPresenter
import com.cupcake.todo.ui.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), IDetailsView {

    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentDetailsBinding =
        FragmentDetailsBinding::inflate

    private lateinit var presenter: DetailsPresenter
    private lateinit var task: Task

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = DetailsPresenter(this)

        task = (arguments?.getParcelable(TASK_DETAILS) as Task?)!!

        if (task is TaskPersonal) {
            binding.recyclerViewDetails.isGone = true
        } else {
            val mAdapter = DetailsAdapter(presenter.assignee, presenter.assignee[2])
            binding.recyclerViewDetails.adapter = mAdapter
        }

        initSpinner()

        binding.apply {
            textViewTitle.text = task.title
            textViewDetails.text = task.description
            textViewDate.text = task.createTime
        }

    }

    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "hideLoading")
    }

    override fun onUpDateSuccess() {
        Log.v(LOG_TAG, "onUpdateSuccess")
    }

    override fun onUpDateFailure(error: String) {
        Log.v(LOG_TAG, "onUpdateFailure $error")
    }

    private fun initSpinner() {

        val items = listOf("To Do", "In progress", "Done")

        val mAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)

        binding.dropdownMenu.apply {
            setAdapter(mAdapter)
            task.status?.let { setSelection(it.toInt()) }
            onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    task.id?.let { presenter.DetailsupDate(it, position) }
                }
        }
    }

    companion object {
        private const val TASK_DETAILS = "details_task"
        fun newInstance(task: Task) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(TASK_DETAILS, task)
            }
        }
    }
}