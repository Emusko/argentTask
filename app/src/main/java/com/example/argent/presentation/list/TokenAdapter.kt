package com.example.argent.presentation.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import argent.R
import argent.databinding.ItemListViewBinding
import com.example.argent.data.service.model.remote.response.GetTokensResponseModel
import com.google.gson.Gson
import java.util.*

class TokenAdapter(
    private var tokens: ArrayList<GetTokensResponseModel.Tokens>
) : RecyclerView.Adapter<TokenAdapter.FormAListViewHolder>() {

    private var filteredList: ArrayList<GetTokensResponseModel.Tokens> = tokens
    private val filter = FilterProducts(tokens, this)

    class FormAListViewHolder(private val itemListViewBinding: ItemListViewBinding) :
        RecyclerView.ViewHolder(itemListViewBinding.root) {
        fun bind(
            tokens: GetTokensResponseModel.Tokens?
        ) {
            val price = if (tokens?.price !is Boolean) {
                Gson().fromJson(
                    tokens?.price.toString(),
                    GetTokensResponseModel.Price::class.java
                ).diff30d ?: 0.00
            } else {
                0.00
            }
            itemListViewBinding.amount.text = itemListViewBinding.root.context.resources.getString(
                R.string.amount_format,
                tokens?.name,
                price.toString().format(2)
            )
            if (price.compareTo(0) < 0.0)
                itemListViewBinding.amount.setTextColor(
                    ContextCompat.getColor(
                        itemListViewBinding.root.context,
                        R.color.lostColor
                    )
                )
            else if (price.compareTo(0) > 0.0)
                itemListViewBinding.amount.setTextColor(
                    ContextCompat.getColor(
                        itemListViewBinding.root.context,
                        R.color.gainColor
                    )
                )
            else
                itemListViewBinding.amount.setTextColor(
                    ContextCompat.getColor(
                        itemListViewBinding.root.context,
                        R.color.neutralColor
                    )
                )
            itemListViewBinding.name.text = itemListViewBinding.root.context.resources.getString(
                R.string.balance_format,
                tokens?.name
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FormAListViewHolder(
            ItemListViewBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_view, parent, false)
            )
        )

    override fun onBindViewHolder(holder: FormAListViewHolder, position: Int) {
        holder.bind(filteredList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: ArrayList<GetTokensResponseModel.Tokens>) {
        this.filteredList = list
        notifyDataSetChanged()
    }

    fun filterList(text: String?) {
        if (text.isNullOrEmpty())
            setList(tokens)
        else
            filter.filter(text)
    }

    override fun getItemCount() = filteredList.size
    class FilterProducts(
        private val expandableItems: ArrayList<GetTokensResponseModel.Tokens>,
        private val adapter: TokenAdapter
    ) : Filter() {
        private var filteredList = arrayListOf<GetTokensResponseModel.Tokens>()

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            filteredList.clear()
            val results = FilterResults()
            expandableItems.forEach {
                val productName = it.name
                if (!constraint.isNullOrEmpty() && !productName.isNullOrEmpty()) {
                    if (productName.lowercase(Locale.ROOT).contains(
                            (constraint as String).lowercase(
                                Locale.ROOT
                            )
                        )
                    ) {
                        filteredList.add(it)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size
            return results
        }

        @Suppress("UNCHECKED_CAST")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            adapter.setList(results?.values as ArrayList<GetTokensResponseModel.Tokens>)
        }

    }
}