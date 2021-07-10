package com.sugarspoon.easyfinance.ui.dashboard

import com.github.mikephil.charting.data.PieData

sealed class DashBoardStates {
    object GoodHealth : DashBoardStates()
    object BadHealth : DashBoardStates()
    object BalancedHealth : DashBoardStates()
    object DisplayPieChartNoData : DashBoardStates()
    data class LoadSpending(val spendingList: List<SpendingAdapterTypes>) : DashBoardStates()
    data class DisplaySpendingValue(val value: Float) : DashBoardStates()
    data class DisplayRevenue(val value: Float) : DashBoardStates()
    data class DisplayPieChart(val data: PieData) : DashBoardStates()
}
