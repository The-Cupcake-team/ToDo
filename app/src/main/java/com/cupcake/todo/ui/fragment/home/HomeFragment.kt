package com.cupcake.todo.ui.fragment.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cupcake.todo.databinding.FragmentHomeBinding
import com.cupcake.todo.model.network.response.PersonalTask
import com.cupcake.todo.presenter.home.HomePresenter
import com.cupcake.todo.model.network.response.TeamTask
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.util.toPersonalTask
import okhttp3.internal.notify


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
        itemsList.add(HomeItem("Asia", HomeItemType.ITEM_TYPE_HEADER_DETAILS))
        itemsList.add(HomeItem("Recent task", HomeItemType.ITEM_TYPE_TITLE_SECTION))
        itemsList.addAll(personalTasks.map { it.toPersonalTask() })
    }

    override fun onToDoPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
    }

    override fun onToDoTeamTasksSuccess(teamTasks: List<TeamTask>) {
    }


    override fun onInProgressPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
//        Log.e("result", "onInProgressPersonalTaskSuccess: ${personalTasks}")
    }

    override fun onDonePersonalTaskSuccess(personalTasks: List<PersonalTask>) {
    }


    override fun onInProgressTeamTasksSuccess(teamTasks: List<TeamTask>) {
//        Log.e("result", "onInProgressTeamTasksSuccess: ${teamTasks}")

    }

    override fun onDoneTeamTasksSuccess(teamTasks: List<TeamTask>) {
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


}
