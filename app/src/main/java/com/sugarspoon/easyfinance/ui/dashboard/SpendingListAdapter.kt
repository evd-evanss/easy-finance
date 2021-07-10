package com.sugarspoon.easyfinance.ui.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sugarspoon.easyfinance.R
import com.sugarspoon.easyfinance.base.RecyclerViewAdapter
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity
import com.sugarspoon.easyfinance.data.local.model.CategorySpending
import com.sugarspoon.easyfinance.databinding.EmptyLayoutBinding
import com.sugarspoon.easyfinance.databinding.ItemSpendingBinding
import com.sugarspoon.easyfinance.utils.extensions.formatCurrencyBRL
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class SpendingListAdapter @Inject constructor(
    context: Context,
) : RecyclerViewAdapter(context) {

    var onClickItem: ((SpendingEntity) -> Unit)? = null

    var list = listOf<SpendingAdapterTypes>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override val displayableItemsCount: Int
        get() = list.size

    override fun getItemViewType(position: Int) = when (list[position]) {
        is SpendingAdapterTypes.Empty -> R.layout.empty_layout
        is SpendingAdapterTypes.Spending -> R.layout.item_spending
    }

    override fun getItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_spending -> {
                val view = ItemSpendingBinding.inflate(LayoutInflater.from(context), parent, false).root
                SpendingViewHolder(view)
            }
            else -> {
                val view = EmptyLayoutBinding.inflate(LayoutInflater.from(context), parent, false).root
                EmptyViewHolder(view)
            }
        }
    }

    override fun onBindRecyclerViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
    ) {
        val item = list[position]
        when (holder) {
            is EmptyViewHolder -> holder.bind(item as SpendingAdapterTypes.Empty)
            is SpendingViewHolder -> holder.bind(item as SpendingAdapterTypes.Spending)
        }
    }

    inner class SpendingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SpendingAdapterTypes.Spending) = ItemSpendingBinding.bind(itemView).run {
            itemView.apply {
                backgroundTintList = ContextCompat.getColorStateList(
                    context,
                    CategorySpending.CategoryOption.getColorByType(item.spending.type.orEmpty())
                )
                itemSpendingTypeTv.text = item.spending.type
                itemSpendingDescriptionTv.text = item.spending.description
                itemSpendingValueTv.text = item.spending.value?.formatCurrencyBRL(showCurrency = true)
                setOnClickListener {
                    onClickItem?.invoke(item.spending)
                }
            }
        }
    }

    inner class EmptyViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SpendingAdapterTypes.Empty) = Unit
    }
}