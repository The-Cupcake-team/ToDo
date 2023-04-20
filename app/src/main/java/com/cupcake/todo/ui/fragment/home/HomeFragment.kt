package com.cupcake.todo.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentHomeBinding
import com.cupcake.todo.data.network.response.PersonalTask
import com.cupcake.todo.data.network.response.TeamTask
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.details.DetailsFragment
import com.cupcake.todo.ui.util.extension.hidden
import com.cupcake.todo.ui.util.extension.show
import com.cupcake.todo.ui.fragment.tasks.TasksFragment
import com.cupcake.todo.ui.util.extension.navigateTo
import com.cupcake.todo.ui.util.extension.toPersonalTask
import com.cupcake.todo.util.PrefsUtil


class HomeFragment : BaseFragment<FragmentHomeBinding>(), IHomeView {

    private lateinit var homeAdapter: HomeAdapter
    private var itemsList: MutableList<HomeItem<Any>> = mutableListOf()
    private val presenter: HomePresenter = HomePresenter(this)

    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.getAllTasks()

    }

    private fun setupRecyclerView() {
        activity?.runOnUiThread {
            homeAdapter = HomeAdapter(
                itemsList,
                ::onClickViewMore,
                ::onClickPersonalTaskItem,
                ::onClickTeamTaskItem,
                ::onClickPlanItem
            )
            binding.nestedRecyclerHome.adapter = homeAdapter
        }
    }


    override fun getTaskStatusCounts(personalTasks: Triple<Float, Float, Float>) {
        val details = mapOf(
            USERNAME to PrefsUtil.userName,
            TODO to personalTasks.first,
            IN_PROGRESS to personalTasks.second,
            DONE to personalTasks.third
        )
        itemsList.add(0, HomeItem(details, HomeItemType.ITEM_TYPE_HEADER_DETAILS))
        setupRecyclerView()

    }

    override fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>) {
        val titleHeader = context?.getString(R.string.team_task)
        itemsList.add(HomeItem(titleHeader!!, HomeItemType.ITEM_TYPE_TITLE_SECTION))
        itemsList.add(HomeItem(teamTasks, HomeItemType.ITEM_TYPE_TEAM_TASK))
    }


    override fun onRecentPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        val titleHeader = context?.getString(R.string.recent_task)
        itemsList.add(HomeItem(titleHeader!!, HomeItemType.ITEM_TYPE_TITLE_SECTION))
        itemsList.addAll(personalTasks.map { it.toPersonalTask() })
    }

    override fun onGetDataFailure(error: String) {
        Log.e(LOG_TAG, "onGetDataFailure${error}")
        presenter.getAllTasks()
        itemsList.clear()

    }

    override fun showLoading() {
        binding.loading.root.show()

    }

    override fun hideLoading() {
        binding.loading.root.hidden()
    }

    private fun onClickViewMore() {
        navigateTo(TasksFragment())
    }

    private fun onClickPersonalTaskItem(personalTask: PersonalTask) {
        navigateTo(DetailsFragment.newInstance(null, personalTask))

    }

    private fun onClickTeamTaskItem(teamTask: TeamTask) {
        navigateTo(DetailsFragment.newInstance(teamTask, null))
    }

    private fun onClickPlanItem(isPersonalPlane: Boolean) {
        navigateTo(TasksFragment())
    }


    companion object {
        const val USERNAME = "username"
        const val TODO = "toDo"
        const val IN_PROGRESS = "inProgress"
        const val DONE = "done"
        const val RECENT_TASK = "Recent task"
        const val LOG_TAG = "TAG"
        const val PERSONAL_TASK_DATA = "personal task data"
        const val TEAM_TASK_DATA = "team task data"

    }


}


