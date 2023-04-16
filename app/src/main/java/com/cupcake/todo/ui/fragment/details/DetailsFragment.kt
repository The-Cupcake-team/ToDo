package com.cupcake.todo.ui.fragment.details

import android.view.LayoutInflater
import android.view.ViewGroup
import com.cupcake.todo.databinding.FragmentDetailsBinding
import com.cupcake.todo.ui.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> FragmentDetailsBinding =
        FragmentDetailsBinding::inflate

}