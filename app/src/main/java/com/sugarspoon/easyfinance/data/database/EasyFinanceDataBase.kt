package com.sugarspoon.easyfinance.data.database

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sugarspoon.easyfinance.data.dao.RevenueDao
import com.sugarspoon.easyfinance.data.dao.SpendingDao
import com.sugarspoon.easyfinance.data.local.entity.RevenueEntity
import com.sugarspoon.easyfinance.data.local.entity.SpendingEntity

@Database(entities = [SpendingEntity::class, RevenueEntity::class], version = 1, exportSchema = false)
abstract class EasyFinanceDataBase : RoomDatabase() {

    abstract fun spendingDao() : SpendingDao

    abstract fun revenueDao() : RevenueDao

    companion object {
        @Volatile
        private var INSTANCE: EasyFinanceDataBase? = null

        fun getDatabase(context: Context): EasyFinanceDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EasyFinanceDataBase::class.java,
                    "easy_finance_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("CREATE TABLE IF NOT EXISTS `revenue_table` (`id` INTEGER, PRIMARY KEY(`id`))")
            }
        }

    }
}