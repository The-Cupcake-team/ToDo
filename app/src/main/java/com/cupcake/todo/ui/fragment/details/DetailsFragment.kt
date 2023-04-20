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
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.details.adapter.DetailsAdapter
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), IDetailsView {

    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDetailsBinding =
        FragmentDetailsBinding::inflate

    private val teamTask by lazy { arguments?.getParcelable<TeamTask>(TEAM_TASK_DETAILS) }
    private val personalTask by lazy { arguments?.getParcelable<PersonalTask>(PERSONAL_TASK_DETAILS) }

    private lateinit var presenter: DetailsPresenter

    //    private val toDo = context?.getString(R.string.to_do)
//    private val inProgress = context?.getString(R.string.in_progress)
//    private val done = context?.getString(R.string.done)
    private val items = listOf("toDo", "inProgress", "done")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = DetailsPresenter(this)
        binding.textViewDetails.movementMethod = ScrollingMovementMethod()
        bindTasksData()
        initSpinner()
        setupBackButton()
    }

    private fun bindTasksData() {
        teamTask?.let {
            val mAdapter = DetailsAdapter(presenter.team, it.assignee)
            binding.recyclerViewDetails.adapter = mAdapter
            binding.apply {
                textViewTitle.text = it.title
                textViewDetails.text = it.description
                textViewDate.text = it.createTime
            }
        } ?: binding.apply {
            textViewTitle.text = personalTask?.title
            textViewDetails.text = personalTask?.description
            textViewDate.text = personalTask?.createTime
            recyclerViewDetails.visibility = View.GONE
        }
    }

    override fun showLoading() {
        requireActivity().runOnUiThread {
            binding.progressBarLoading.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        requireActivity().runOnUiThread {
            binding.progressBarLoading.visibility = View.GONE
        }
    }

    override fun onUpDateSuccess() {
        Log.v(LOG_TAG, "onUpdateSuccess")
    }

    override fun onUpDateFailure(error: String) {
        Log.v(LOG_TAG, "onUpdateFailure $error")
    }

    private fun initSpinner() {

        val mAdapter = ArrayAdapter(requireContext(), R.layout.drop_down_list_item, items)

        binding.dropdownMenu.apply {

            teamTask?.let { setText(items[it.status]) }
            personalTask?.let { setText(items[it.status]) }

            setAdapter(mAdapter)

            showDropdown(mAdapter)

            onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                teamTask?.id?.let { presenter.detailsUpDate(it, position, false) }
                personalTask?.id?.let { presenter.detailsUpDate(it, position, true) }
                val changeStatusTo = requireActivity().getString(R.string.change_status_to)
                view?.let {
                    Snackbar.make(
                        it.rootView,
                        "$changeStatusTo ${items[position]}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun AutoCompleteTextView.showDropdown(adapter: ArrayAdapter<String>?) {
        if (!TextUtils.isEmpty(this.text.toString())) {
            adapter?.filter?.filter(null)
        }
    }

    companion object {
        private const val TEAM_TASK_DETAILS = "team_task"
        private const val PERSONAL_TASK_DETAILS = "personal_task"
        fun newInstance(teamTask: TeamTask? = null, personalTask: PersonalTask? = null) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    teamTask?.let { putParcelable(TEAM_TASK_DETAILS, it) }
                    personalTask?.let { putParcelable(PERSONAL_TASK_DETAILS, it) }
                }
            }
    }

    override fun onResume() {
        super.onResume()
        initSpinner()
    }

    private fun setupBackButton() {
        binding.toolBarDetails.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}