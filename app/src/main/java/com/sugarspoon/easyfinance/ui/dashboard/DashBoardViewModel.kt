package com.sugarspoon.easyfinance.ui.dashboard

import android.graphics.Color
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.MPPointF
import com.sugarspoon.easyfinance.base.BaseViewModel
import com.sugarspoon.easyfinance.data.local.entity.RevenueEntity
import com.sugarspoon.easyfinance.data.repository.LocalRepository
import com.sugarspoon.easyfinance.utils.PieChartFilter
import com.sugarspoon.easyfinance.utils.extensions.orEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashBoardViewModel @Inject constructor(
    private val repository: LocalRepository
) : BaseViewModel<DashBoardIntent, DashBoardStates>() {

    private var spendingTotal: Float = 0f
    private var revenueTotal: Float = 0f
    private var revenueEntity: RevenueEntity? = null
    private val spendingEntries = mutableListOf<PieEntry>()

    override fun handle(intent: DashBoardIntent) {
        when(intent) {
            is DashBoardIntent.ResumeData -> resumeData()
        }
    }

    private fun resumeData() {
        getSpending()
        getRevenue()
    }

    private fun getSpending() = viewModelScope.launch {
        val spendingList = repository.getAllSpending()
        val spending = mutableListOf<SpendingAdapterTypes>()
        spendingList.forEach {
            spending.add(SpendingAdapterTypes.Spending(it))
        }
        _state.value =
        if (spendingList.isNotEmpty()) {
            DashBoardStates.LoadSpending(
                spendingList = spending
            )
        } else {
            DashBoardStates.LoadSpending(
                spendingList = listOf(SpendingAdapterTypes.Empty)
            )
        }
        spendingList.forEach {
            spendingTotal += it.value.orEmpty()
        }

        val filterPieChart = PieChartFilter()
        val categories = filterPieChart.filterByCategory(
            list = spendingList
        )

        val dataSet = PieDataSet(categories, "Saídas")
        dataSet.setDrawIcons(false)
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0F, 40F)
        dataSet.selectionShift = 5f
        dataSet.valueTextSize = 16f
        dataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        val data = PieData(dataSet)
        data.setValueTextSize(11f)
        data.setValueTextColor(Color.WHITE)
        _state.value = if (spendingList.isEmpty()) {
            DashBoardStates.DisplayPieChartNoData
        } else {
            DashBoardStates.DisplayPieChart(data = data)
        }
        _state.value = DashBoardStates.DisplaySpendingValue(
            value = spendingTotal
        )
    }

    private fun checkFinanceHealth() = viewModelScope.launch {
        _state.value = when {
            revenueTotal == spendingTotal -> DashBoardStates.BalancedHealth
            revenueTotal > spendingTotal -> DashBoardStates.GoodHealth
            else -> DashBoardStates.BadHealth
        }
    }

    private fun getRevenue() = viewModelScope.launch {
        revenueEntity = repository.getRevenue(id = 0)
        revenueTotal = revenueEntity?.value?.orEmpty() ?: 0f
        _state.value = DashBoardStates.DisplayRevenue(value = revenueEntity?.value.orEmpty())
        checkFinanceHealth()
    }
}