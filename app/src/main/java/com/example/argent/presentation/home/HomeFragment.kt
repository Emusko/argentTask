package com.example.argent.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import argent.R
import argent.databinding.FragmentHomeBinding
import com.example.argent.di.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentBinding.tokenNavigateButton.setOnClickListener {
            findNavController().navigate(R.id.token_list_fragment)
        }
    }

    override fun getLayoutId() =
        R.layout.fragment_home


    override fun getViewModel() = ViewModelProvider(this, factory)[HomeViewModel::class.java]
}