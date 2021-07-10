package com.sugarspoon.easyfinance.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity

@Dao
interface SpendingDao {

    @Insert
    suspend fun insertSpending(spending: SpendingEntity)

    @Delete
    suspend fun delete(spending: SpendingEntity)

    @Query("DELETE FROM spending_table")
    fun deleteAll()

    @Query("SELECT * FROM spending_table WHERE id = :id")
    suspend fun getSpendingById(id: Int): SpendingEntity

    @Query("SELECT * FROM spending_table")
    suspend fun getAll(): List<SpendingEntity>

    @Query("SELECT * FROM spending_table ORDER BY id DESC LIMIT 1")
    suspend fun getLastSpendingAdd(): SpendingEntity
}

