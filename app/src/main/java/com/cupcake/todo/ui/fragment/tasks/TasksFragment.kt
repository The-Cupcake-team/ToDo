package com.cupcake.todo.ui.fragment.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.cupcake.todo.R
import com.cupcake.todo.databinding.FragmentTasksBinding
import com.cupcake.todo.ui.base.BaseFragment
import com.cupcake.todo.ui.fragment.add_task.AddPersonalTaskFragment
import com.cupcake.todo.ui.fragment.add_team_task.AddTeamTaskFragment
import com.cupcake.todo.ui.fragment.personal_tasks.PersonalTasksFragment
import com.cupcake.todo.ui.fragment.tasks.adapter.ViewPagerTasksAdapter
import com.cupcake.todo.ui.fragment.team_tasks.TeamTasksFragment
import com.cupcake.todo.ui.util.isPersonalTabTaskSelected
import com.cupcake.todo.ui.util.navigateTo
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs


class TasksFragment : BaseFragment<FragmentTasksBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentTasksBinding
        get() = FragmentTasksBinding::inflate

    private val addPersonalTask by lazy { AddPersonalTaskFragment() }
    private val addTeamTask by lazy { AddTeamTaskFragment() }

    private val fragmentTasks = mapOf(
        0 to PersonalTasksFragment(),
        1 to TeamTasksFragment(),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val titleTabTasks = resources.getStringArray(R.array.task)
        setupBackButton()
        val adapter = ViewPagerTasksAdapter(
            fragmentManager = requireActivity().supportFragmentManager,
            fragmentItems = fragmentTasks,
            lifecycle = lifecycle,
        )
        binding.apply {
            viewPagerTasks.adapter = adapter
            TabLayoutMediator(tabTasks, viewPagerTasks) { tab, position ->
                tab.text = titleTabTasks[position]
            }.attach()
            setUpTransformerViewPager(viewPagerTasks)
            buttonAddTask.root.setOnClickListener {
                onClickCreateTask(tabTasks)
            }
        }
    }
    private fun setUpTransformerViewPager(viewPager: ViewPager2) {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(20))
        transformer.addTransformer { view, position ->
            if (position <= -1.0F || position >= 1.0F) {
                view.alpha = 0.0F
                view.visibility = View.GONE
            } else if (position == 0.0F) {
                view.alpha = 1.0F
                view.visibility = View.VISIBLE
            } else {
                view.alpha = 1.0F - abs(position)
                view.visibility = View.VISIBLE
            }
        }
        viewPager.setPageTransformer(transformer)
    }

    private fun onClickCreateTask(tabTasks: TabLayout) {
        if (tabTasks.isPersonalTabTaskSelected()) {
            this.navigateTo(addPersonalTask)
        } else {
            this.navigateTo(addTeamTask)
        }
    }

    private fun setupBackButton() {
        binding.toolBarTask.setNavigationOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}






