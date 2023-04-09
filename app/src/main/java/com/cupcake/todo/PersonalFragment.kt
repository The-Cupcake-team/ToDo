package com.cupcake.todo


import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentPersonalBinding


class PersonalFragment : BaseFragment<FragmentPersonalBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentPersonalBinding
        get() = FragmentPersonalBinding::inflate
}