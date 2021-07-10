package com.sugarspoon.easyfinance.data.local.model

data class SpendingModel(
    val id: Int,
    val value: Float,
    val description: String,
    val type: String
)