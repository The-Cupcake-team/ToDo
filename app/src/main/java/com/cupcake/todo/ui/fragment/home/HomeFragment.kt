package com.cupcake.todo.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentHomeBinding
import com.cupcake.todo.ui.base.BaseFragment


class HomeFragment() : BaseFragment<FragmentHomeBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentHomeBinding =
        FragmentHomeBinding::inflate

}