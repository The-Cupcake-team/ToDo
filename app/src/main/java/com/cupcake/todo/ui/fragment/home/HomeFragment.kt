package com.cupcake.todo.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentHomeBinding
import com.cupcake.todo.ui.base.BaseFragment


class HomeFragment() : BaseFragment<FragmentHomeBinding>(), IHomeView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun onRegisterSuccess() {
        TODO("Not yet implemented")
    }

    override fun onRegisterFailure(error: String) {
        TODO("Not yet implemented")
    }

}
