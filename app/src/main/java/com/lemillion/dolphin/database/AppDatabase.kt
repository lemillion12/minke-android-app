package com.lemillion.dolphin.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.lemillion.dolphin.dao.AccountDao
import com.lemillion.dolphin.dao.UnenrichedTransactionDao
import com.lemillion.dolphin.database.SeedDatabaseWorker.Companion.KEY_FILENAME
import com.lemillion.dolphin.entity.Account
import com.lemillion.dolphin.entity.Transaction
import com.lemillion.dolphin.entity.UnenrichedTransaction
import com.lemillion.dolphin.utilities.SAMPLE_ACCOUNT_DATA_FILENAME
import com.lemillion.dolphin.utilities.DATABASE_NAME

@Database(
    entities = arrayOf(Account::class, Transaction::class, UnenrichedTransaction::class),
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountDao(): AccountDao
    abstract fun getUnenrichedTransactionDao(): UnenrichedTransactionDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to SAMPLE_ACCOUNT_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}