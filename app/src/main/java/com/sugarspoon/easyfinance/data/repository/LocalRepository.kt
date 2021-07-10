package com.sugarspoon.easyfinance.data.repository

import com.sugarspoon.easyfinance.data.database.EasyFinanceDataBase
import com.sugarspoon.easyfinance.data.local.entity.RevenueEntity
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val dataBase: EasyFinanceDataBase,
) {

    suspend fun getAllSpending() = dataBase.spendingDao().getAll()

    suspend fun insertSpending(spending: SpendingEntity) =
        dataBase.spendingDao().insertSpending(spending = spending)

    suspend fun getLastTaskAdd() = dataBase.spendingDao().getLastSpendingAdd()

    suspend fun deleteAll() = dataBase.spendingDao().deleteAll()

    suspend fun delete(spending: SpendingEntity) = dataBase.spendingDao().delete(spending)

    suspend fun setValue(revenue: RevenueEntity) = dataBase.revenueDao().insertValue(revenue)

    suspend fun updateValue(id: Int, value: Float) = dataBase.revenueDao().updateValue(
        id = id,
        value = value
    )

    suspend fun getRevenue(id: Int) = dataBase.revenueDao().getRevenue(id)
}