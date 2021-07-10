package com.sugarspoon.easyfinance.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.LayoutDirection
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.sugarspoon.easyfinance.R
import com.sugarspoon.easyfinance.base.BaseFragment
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity
import com.sugarspoon.easyfinance.databinding.FragmentDashboardBinding
import com.sugarspoon.easyfinance.utils.extensions.formatCurrencyBRL
import com.sugarspoon.easyfinance.utils.extensions.setVisible
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashBoardFragment : BaseFragment<FragmentDashboardBinding>(
    FragmentDashboardBinding::inflate
) {

    private val viewModel: DashBoardViewModel by viewModels()

    @Inject
    lateinit var spendingAdapter: SpendingListAdapter

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        setView()
        setListeners()
        setObservers()
        viewModel.handle(DashBoardIntent.ResumeData)
    }

    private fun setView() = binding.run {
        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false,
        )
        dashBoardOutputRv.apply {
            adapter = spendingAdapter
            setLayoutManager(layoutManager)
        }
    }

    private fun setListeners() = binding.run {

    }

    private fun setObservers() = viewModel.state.observe(viewLifecycleOwner) { state ->
        when (state) {
            is DashBoardStates.DisplaySpendingValue -> displaySpendingValue(state.value)
            is DashBoardStates.DisplayRevenue -> displayRevenueValue(state.value)
            is DashBoardStates.LoadSpending -> loadSpending(state.spendingList)
            is DashBoardStates.GoodHealth -> goodFinance()
            is DashBoardStates.BadHealth -> badFinance()
            is DashBoardStates.BalancedHealth -> balancedFinance()
            is DashBoardStates.DisplayPieChart -> displayPieChart(state.data)
            is DashBoardStates.DisplayPieChartNoData -> displayPieChartNoData()
        }
    }

    private fun displayPieChart(data: PieData) = binding.run {
        dashBoardPieChart.data = data
        val description = Description()
        description.text = "Gastos"
        description.textSize = 18f
        description.typeface = Typeface.DEFAULT
        dashBoardPieChart.description = description
        dashBoardPieChart.highlightValues(null)
        dashBoardPieChart.invalidate()
        dashBoardPieChart.animateXY(5000, 5000)
        binding.dashBoardNoDataTv.setVisible(false)
    }

    private fun displayPieChartNoData() = binding.dashBoardPieChart.apply {
        setNoDataText("")
        setNoDataTextColor(R.color.color_primary)
        setNoDataTextTypeface(Typeface.DEFAULT_BOLD)
        invalidate()
        binding.dashBoardNoDataTv.setVisible(true)
    }

    private fun displaySpendingValue(value: Float) = binding.run {
        dashBoardSpendingValueTv.text = value.formatCurrencyBRL()
    }

    private fun displayRevenueValue(value: Float) = binding.run {
        dashBoardIncomeValueTv.text = value.formatCurrencyBRL()
    }

    private fun loadSpending(spendingList: List<SpendingAdapterTypes>) {
        spendingAdapter.list = spendingList
    }

    private fun goodFinance() = binding.run {
        dashBoardStatusTv.text = getString(R.string.good_finance)
    }

    private fun balancedFinance() = binding.run {
        dashBoardStatusTv.text = getString(R.string.balanced_finance)
    }

    @SuppressLint("WrongConstant")
    private fun badFinance() = binding.run {
        dashBoardStatusTv.text = getString(R.string.bad_finance)
    }
}