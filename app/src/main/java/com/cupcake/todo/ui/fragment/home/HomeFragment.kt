package com.cupcake.todo.ui.fragment.home

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.cupcake.todo.databinding.FragmentHomeBinding
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.presenter.home.HomePresenter
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.details.DetailsFragment
import com.cupcake.todo.ui.fragment.personal_tasks.PersonalTasksFragment
import com.cupcake.todo.ui.fragment.team_tasks.TeamTasksFragment
import com.cupcake.todo.ui.util.toPersonalTask


class HomeFragment : BaseFragment<FragmentHomeBinding>(), IHomeView {

    private lateinit var homeAdapter: HomeAdapter
    private var itemsList: MutableList<HomeItem<Any>> = mutableListOf()
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomePresenter(this).getTasks()

    }

    private fun setupRecyclerView() {
        activity?.runOnUiThread {
            homeAdapter = HomeAdapter(itemsList, ::onClickViewMore, ::onClickPersonalTaskItem, ::onClickTeamTaskItem)
            binding.nestedRecyclerHome.adapter = homeAdapter
        }
    }


    override fun getTaskStatusCounts(personalTasks: Triple<Float, Float, Float>) {
        val details = mapOf(
            USERNAME to "Asia",
            TODO to personalTasks.first,
            IN_PROGRESS to personalTasks.second,
            DONE to personalTasks.third
        )
        itemsList.add(0, HomeItem(details, HomeItemType.ITEM_TYPE_HEADER_DETAILS))
        setupRecyclerView()

    }
    override fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>) {
        itemsList.add(HomeItem(TEAM_TASK, HomeItemType.ITEM_TYPE_TITLE_SECTION))
        itemsList.add(HomeItem(teamTasks, HomeItemType.ITEM_TYPE_TEAM_TASK))
    }


    override fun onRecentPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        itemsList.add(HomeItem(RECENT_TASK, HomeItemType.ITEM_TYPE_TITLE_SECTION))
        itemsList.addAll(personalTasks.map { it.toPersonalTask() })
    }

    override fun onGetDataFailure(error: String) {
        Log.e("result", "onGetDataFailure${error}")
    }

    override fun showLoading() {
        Log.e("result", "showLoading(")
    }

    override fun hideLoading() {
        Log.e("result", "hideLoading(")
    }

    private fun onClickViewMore(planeType: String) {
        when (planeType) {
            RECENT_TASK -> addFragment(PersonalTasksFragment())
            else -> addFragment(TeamTasksFragment())
        }
    }

    private fun onClickPersonalTaskItem(personalTask: PersonalTask){
        addFragmentWithSendObject(DetailsFragment(), PERSONAL_TASK_DATA, personalTask)
    }
    private fun onClickTeamTaskItem(teamTask: TeamTask){
        addFragmentWithSendObject(DetailsFragment(), TEAM_TASK_DATA, teamTask)
    }


    companion object {
        const val USERNAME = "username"
        const val TODO = "toDo"
        const val IN_PROGRESS = "inProgress"
        const val DONE = "done"
        const val TEAM_TASK = "Team task"
        const val RECENT_TASK = "Recent task"

        const val PERSONAL_TASK_DATA = "personal task data"
        const val TEAM_TASK_DATA = "team task data"
    }

    fun addFragmentWithSendObject(fragment: Fragment, key: String, task: Any? = null) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        task.let {
            val bundle = Bundle()
            bundle.putParcelable(key, task as Parcelable?)
            fragment.arguments = bundle
        }
        transaction.apply {
            replace(android.R.id.content, fragment)
            addToBackStack(null)
            commit()
        }
    }

    fun addFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()

        transaction.apply {
            replace(android.R.id.content, fragment)
            addToBackStack(null)
            commit()
        }
    }
}


