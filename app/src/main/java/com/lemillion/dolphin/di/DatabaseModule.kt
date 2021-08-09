package com.lemillion.dolphin.di

import android.content.Context
import com.lemillion.dolphin.data.dao.AccountDao
import com.lemillion.dolphin.data.dao.UnenrichedTransactionDao
import com.lemillion.dolphin.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun providePlantDao(appDatabase: AppDatabase): AccountDao {
        return appDatabase.getAccountDao()
    }

    @Provides
    fun provideGardenPlantingDao(appDatabase: AppDatabase): UnenrichedTransactionDao {
        return appDatabase.getUnenrichedTransactionDao()
    }
}