package com.sugarspoon.easyfinance.ui.dashboard

import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity

sealed class SpendingAdapterTypes {
    data class Spending(val spending: SpendingEntity) : SpendingAdapterTypes()
    object Empty : SpendingAdapterTypes()
}
