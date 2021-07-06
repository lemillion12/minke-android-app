package com.lemillion.dolphin.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.lemillion.dolphin.data.AccountDao
import com.lemillion.dolphin.data.UnenrichedTransactionDao
import com.lemillion.dolphin.database.SeedDatabaseWorker.Companion.KEY_FILENAME
import com.lemillion.dolphin.entity.Account
import com.lemillion.dolphin.entity.Transaction
import com.lemillion.dolphin.entity.UnenrichedTransaction
import com.lemillion.dolphin.utilities.DATABASE_NAME
import com.lemillion.dolphin.utilities.LocalDateConverter
import com.lemillion.dolphin.utilities.SAMPLE_ACCOUNT_DATA_FILENAME

@Database(
    entities = arrayOf(Account::class, Transaction::class, UnenrichedTransaction::class),
    version = 1
)
@TypeConverters(LocalDateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getAccountDao(): AccountDao
    abstract fun getUnenrichedTransactionDao(): UnenrichedTransactionDao

    companion object {
        private const val TAG = "AppDatabase"

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
                            initializeDatabase(context)
                        }
                    }
                )
                .build()
        }

        fun initializeDatabase(context: Context) {
            Log.i(TAG, "Database creation - Started")
            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                .setInputData(workDataOf(KEY_FILENAME to SAMPLE_ACCOUNT_DATA_FILENAME))
                .build()
            WorkManager.getInstance(context).enqueue(request)
            Log.i(TAG, "Database creation - Ended")
        }
    }
}