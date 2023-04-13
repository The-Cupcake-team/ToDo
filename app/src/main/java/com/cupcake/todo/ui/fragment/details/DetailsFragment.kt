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
        arguments.let {
            task = (it?.getSerializable(TAG) as Task?)!!
        }

// check recycler visible or adapter
        if (task is TaskPersonal) {
            binding.recyclerViewDetails.isGone = true
        } else {
            //get team list
            val madapter = DetailsAdapter(presenter.assignee,presenter.assignee.get(2))
            binding.recyclerViewDetails.adapter = madapter
        }

        initSpinner()

//show data of task
     binding.textViewTitle.text = task.title
        binding.textViewDetails.text = task.description
        binding.textViewDate.text = task.createTime

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

        val mAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)

        binding.spinnerStatus.apply {
            adapter = mAdapter

            // default of spinner
            binding.spinnerStatus.setSelection(task.status)

            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                         presenter.DetailsupDate(task.id, position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                    // default of spinner
                    binding.spinnerStatus.setSelection(task.status)
                }

            }
        }
    }

    companion object {
        const val TAG = "Details Fragment Tag"
        fun newInstance(task: Task) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(TAG, task)
            }
        }
    }
}