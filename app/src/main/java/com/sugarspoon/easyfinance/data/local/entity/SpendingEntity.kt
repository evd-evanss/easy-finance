package com.sugarspoon.easyfinance.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spending_table")
data class SpendingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "value")
    val value: Float? = 0f,
    @ColumnInfo(name = "description")
    val description: String? = "",
    @ColumnInfo(name = "type")
    val type: String? = ""
)