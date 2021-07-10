package com.sugarspoon.easyfinance.di.modules

import android.content.Context
import com.sugarspoon.easyfinance.data.database.EasyFinanceDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDailyDatabase(@ApplicationContext context: Context): EasyFinanceDataBase {
        return EasyFinanceDataBase.getDatabase(context)
    }
}