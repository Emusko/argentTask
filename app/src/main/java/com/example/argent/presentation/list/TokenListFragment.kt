package com.example.argent.presentation.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import argent.R
import argent.databinding.FragmentTokenListBinding
import com.example.argent.di.base.BaseFragment

class TokenListFragment : BaseFragment<FragmentTokenListBinding, TokenListViewModel>() {

    private lateinit var binding: FragmentTokenListBinding

    private lateinit var adapter: TokenAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = getBinding()

        setLinearLayout()
        setOutputListeners()
        setInputListeners()
    }

    override fun getLayoutId() =
        R.layout.fragment_token_list

    override fun getViewModel() = ViewModelProvider(this, factory)[TokenListViewModel::class.java]


    private fun setInputListeners() {
        fragmentViewModel.getTokens()

    }

    private fun setLinearLayout() {
        val linearLayoutManager =
            LinearLayoutManager(fragmentContext, LinearLayoutManager.VERTICAL, false)
        binding.tokensRecyclerView.layoutManager = linearLayoutManager
    }

    private fun setOutputListeners() {
        fragmentViewModel.tokensLiveData.observe(viewLifecycleOwner) {
            it?.let { tokenResponse ->
                adapter = TokenAdapter(tokenResponse.tokens ?: arrayListOf())
                binding.tokensRecyclerView.adapter = adapter
                binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return onQueryTextChange(query)
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if ((newText?.trim()?.length ?: 0) > 1)
                            adapter.filterList(newText)
                        else
                            adapter.filterList(null)
                        return true
                    }

                })
            }
        }
    }

}