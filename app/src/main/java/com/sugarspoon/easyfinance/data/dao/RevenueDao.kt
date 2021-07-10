package com.sugarspoon.easyfinance.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sugarspoon.easyfinance.data.local.entity.RevenueEntity
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity

@Dao
interface RevenueDao {

    @Insert
    suspend fun insertValue(revenue: RevenueEntity)

    @Delete
    suspend fun delete(revenue: SpendingEntity)

    @Query("DELETE FROM revenue_table")
    fun deleteAll()

    @Query("UPDATE revenue_table SET  value = :value WHERE id = :id")
    suspend fun updateValue(id: Int, value: Float)

    @Query("SELECT * FROM revenue_table WHERE id = :id")
    suspend fun getRevenue(id: Int): RevenueEntity
}

