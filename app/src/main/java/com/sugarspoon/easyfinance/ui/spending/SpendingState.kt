package com.sugarspoon.easyfinance.ui.spending

sealed class SpendingState {
    data class DisplaySpending(val value: Float) : SpendingState()
    object DisplayDescriptionError : SpendingState()
    object DisplayValueError : SpendingState()
    object DisplayTypeError : SpendingState()
    object Success : SpendingState()
}
