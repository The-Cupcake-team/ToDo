package com.cupcake.todo.ui.fragment.home

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


class HomeFragment() : BaseFragment<FragmentHomeBinding>(), IHomeView {

    private var itemsList: MutableList<HomeItem<Any>> = mutableListOf()
    lateinit var progressDialog: ProgressDialog
    private lateinit var homeAdapter: HomeAdapter
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        requireActivity().runOnUiThread {
            HomePresenter(this).getAllPersonalTask()
            HomePresenter(this).getAllTeamTask()
        }
        setupHomeAdapter()


    }



    private fun setupHomeAdapter() {

        itemsList.add(HomeItem("Asia", HomeItemType.ITEM_TYPE_HEADER_DETAILS))

        itemsList.add(HomeItem("Recent task", HomeItemType.ITEM_TYPE_TITLE_SECTION))

        itemsList.add(HomeItem("Team task", HomeItemType.ITEM_TYPE_TITLE_SECTION))


        homeAdapter = HomeAdapter(itemsList, ::onClickViewMore)
        binding.nestedRecyclerHome.adapter = homeAdapter

    }

    fun getAllPersonalTask() {
        HomePresenter(this).getAllPersonalTask()
    }

    override fun onRecentPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
        Log.e("result", "itemsListInRecentTask: ${itemsList}")
        itemsList.addAll(personalTasks.map { it.toPersonalTask() })
        setupHomeAdapter()

    }

    override fun onGetLatestTeamTaskSuccess(teamTasks: List<TeamTask>) {
        itemsList.add(HomeItem(teamTasks, HomeItemType.ITEM_TYPE_TEAM_TASK))
        Log.e("result", "itemsListin onGetLatestTeamTaskSuccess: ${teamTasks}")
    }


    override fun showLoading() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setTitle("Uploading...")
        progressDialog.setMessage("Please wait while the data is being uploaded")
        progressDialog.show()
        Log.e("result", "showLoading(")

    }


    private fun onClickViewMore(planType: String) {

    }

    override fun hideLoading() {
        progressDialog.dismiss()
        Log.e("result", "hideLoading(")
    }


    fun TeamTask.toTeamTask(): HomeItem<Any> {
        return HomeItem(this, HomeItemType.ITEM_TYPE_TEAM_TASK)
    }

    fun PersonalTask.toPersonalTask(): HomeItem<Any> {
        return HomeItem(this, HomeItemType.ITEM_TYPE_PERSONAL_TASK)
    }


    override fun onToDoPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
//        Log.e("result", "onToDoPersonalTaskSuccess: ${personalTasks}")

    }

    override fun onInProgressPersonalTaskSuccess(personalTasks: List<PersonalTask>) {
//        Log.e("result", "onInProgressPersonalTaskSuccess: ${personalTasks}")
    }

    override fun onDonePersonalTaskSuccess(personalTasks: List<PersonalTask>) {
//        Log.e("result", "onDonePersonalTaskSuccess: ${personalTasks}")
    }


    override fun onToDoTeamTasksSuccess(teamTasks: List<TeamTask>) {
//        Log.e("result", "onToDoTeamTasksSuccess: ${teamTasks}")
    }

    override fun onInProgressTeamTasksSuccess(teamTasks: List<TeamTask>) {
//        Log.e("result", "onInProgressTeamTasksSuccess: ${teamTasks}")

    }

    override fun onDoneTeamTasksSuccess(teamTasks: List<TeamTask>) {
//        Log.e("result", "onDoneTeamTasksSuccess: ${teamTasks}")

    }


    override fun onGetDataFailure(error: String) {
        Log.e("result", "onGetDataFailure")
    }


}
