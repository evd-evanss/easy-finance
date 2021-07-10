package com.sugarspoon.easyfinance.ui.spending

sealed class SpendingIntent {
    object OnAddItemCLicked : SpendingIntent()
    object ResumeSpending : SpendingIntent()
    data class OnFillDescription(val description: String) : SpendingIntent()
    data class OnFillValue(val value: Float) : SpendingIntent()
    data class OnFillType(val type: String) : SpendingIntent()
}
