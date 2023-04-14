package com.cupcake.todo.ui.fragment.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentHomeBinding
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.presenter.home.HomePresenter
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.util.toPersonalTask


class HomeFragment() : BaseFragment<FragmentHomeBinding>(), IHomeView {

    private lateinit var homeAdapter: HomeAdapter
    private var itemsList: MutableList<HomeItem<Any>> = mutableListOf()
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        HomePresenter(this).getTasks()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        activity?.runOnUiThread {
            homeAdapter = HomeAdapter(itemsList)
            binding.nestedRecyclerHome.adapter = homeAdapter
        }
    }


    override fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>) {
        itemsList.add(HomeItem("Team task", HomeItemType.ITEM_TYPE_TITLE_SECTION))
        itemsList.add(HomeItem(teamTasks, HomeItemType.ITEM_TYPE_TEAM_TASK))
        setupRecyclerView()

        Log.e("result", "itemsListin onGetLatestTeamTaskSuccess: ${teamTasks}")
    }

    override fun onRecentPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        itemsList.add(HomeItem("Recent task", HomeItemType.ITEM_TYPE_TITLE_SECTION))
        itemsList.addAll(personalTasks.map { it.toPersonalTask() })
    }

    override fun getTaskStatusCounts(personalTasks: Triple<Float, Float, Float>) {
        Log.e(
            "bk", "getTaskStatusCounts: first: " +
                    "${personalTasks.first} " +
                    "third: ${personalTasks.second} third: ${personalTasks.third}"
        )

        val details = mapOf(
            USERNAME to "Asia",
            TODO to personalTasks.first,
            IN_PROGRESS to personalTasks.second,
            DONE to personalTasks.third
        )

        itemsList.add(HomeItem(details, HomeItemType.ITEM_TYPE_HEADER_DETAILS))

    }

    override fun onGetDataFailure(error: String) {
        Log.e("result", "onGetDataFailure${error}")
    }

    override fun onGetDataFailureOnTeam(error: String, statusCode: Int, message: String) {
        Log.e(
            "result",
            "onGetDataFailureOnTeam$error + status code: $statusCode ,+ meesage: $message"
        )
    }

    override fun showLoading() {
        Log.e("result", "showLoading(")

    }

    override fun hideLoading() {
        Log.e("result", "hideLoading(")
    }


    companion object{
        const val USERNAME = "username"
        const val TODO = "toDo"
        const val IN_PROGRESS = "inProgress"
        const val DONE = "done"
    }
}
