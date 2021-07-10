package com.sugarspoon.easyfinance.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "revenue_table")
data class RevenueEntity(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "value")
    val value: Float? = 0f
)