package com.cupcake.todo.ui.fragment.details


import android.os.Bundle
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentDetailsBinding
import com.cupcake.todo.presenter.details.DetailsPresenter
import com.cupcake.todo.presenter.model.PersonalTask
import com.cupcake.todo.presenter.model.Task
import com.cupcake.todo.presenter.model.TeamTask
import com.cupcake.todo.ui.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), IDetailsView {

    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentDetailsBinding =
        FragmentDetailsBinding::inflate

    private lateinit var presenter: DetailsPresenter
    private lateinit var task: Task
    private val items = listOf("To Do", "In progress", "Done")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = DetailsPresenter(this)
        binding.textViewDetails.movementMethod = ScrollingMovementMethod()

        task = (arguments?.getParcelable(TASK_DETAILS) as Task?)!!

        when (task) {
            is TeamTask -> {
                log((task as TeamTask).assignee.toString())
                val mAdapter = DetailsAdapter(presenter.team, (task as TeamTask).assignee as String)
                binding.recyclerViewDetails.adapter = mAdapter
            }
            is PersonalTask -> {
                binding.recyclerViewDetails.visibility = View.GONE
            }
        }


        initSpinner()

        binding.apply {
            textViewTitle.text = task.title
            textViewDetails.text = task.description
            textViewDate.text = task.creationTime
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

        val mAdapter =
            ArrayAdapter(requireContext(), R.layout.drop_down_list_item, items)

        binding.dropdownMenu.setText(items[task.status])

        binding.dropdownMenu.apply {
            setAdapter(mAdapter)
            showDropdown(mAdapter)

            log(task.toString())


            onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    when (task) {
                        is TeamTask -> {
                            task.id?.let { presenter.detailsUpDate(it, position, false) }
                            log("change team task to $position")
                        }
                        is PersonalTask -> {
                            task.id?.let { presenter.detailsUpDate(it, position, true) }
                            log("change personal task to $position")
                        }
                    }
                }
        }
    }

    private fun AutoCompleteTextView.showDropdown(adapter: ArrayAdapter<String>?) {
        if(!TextUtils.isEmpty(this.text.toString())){
            adapter?.filter?.filter(null)
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

    override fun onResume() {
        super.onResume()
        initSpinner()
    }
}