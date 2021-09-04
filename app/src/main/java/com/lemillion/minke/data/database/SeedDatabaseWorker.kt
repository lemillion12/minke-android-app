package com.lemillion.minke.data.database

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.lemillion.minke.data.entity.Account
import com.lemillion.minke.data.entity.UnenrichedTransaction
import com.lemillion.minke.utilities.LocalDateTypeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val accountFilename = inputData.getString(KEY_ACCOUNT_FILENAME)
        val transactionFilename = inputData.getString(KEY_TRANSACTION_FILENAME)
        try {
            if (accountFilename != null && transactionFilename != null) {
                loadAccountData(accountFilename)
                loadTransactionData(transactionFilename)
                Result.success()
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    private suspend fun loadAccountData(filename: String) {
        val accounts = readAccountsDataFromFile(filename)
        val database = AppDatabase.getInstance(applicationContext)
        Log.i(TAG, "Inserting initial transactions")
        database.getAccountDao().insertAll(accounts)
    }

    private suspend fun loadTransactionData(filename: String) {
        val unenrichedTransactions = readTransactionsDataFromFile(filename)
        val database = AppDatabase.getInstance(applicationContext)
        Log.i(TAG, "Inserting initial transactions")
        database.getUnenrichedTransactionDao().insertAll(unenrichedTransactions)
    }

    private fun readAccountsDataFromFile(filename: String): List<Account> {
        applicationContext.assets.open(filename).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val transactionType =
                    object : TypeToken<List<Account>>() {}.type
                val gson: Gson = GsonBuilder()
                    .create()
                return gson.fromJson(jsonReader, transactionType)
            }
        }
    }

    private fun readTransactionsDataFromFile(filename: String): List<UnenrichedTransaction> {
        applicationContext.assets.open(filename).use { inputStream ->
            JsonReader(inputStream.reader()).use { jsonReader ->
                val transactionType =
                    object : TypeToken<List<UnenrichedTransaction>>() {}.type
                val gson: Gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .registerTypeAdapter(
                        LocalDate::class.java,
                        LocalDateTypeAdapter().nullSafe()
                    )
                    .create()
                return gson.fromJson(jsonReader, transactionType)
            }
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_ACCOUNT_FILENAME = "SAMPLE_ACCOUNT_DATA_FILENAME"
        const val KEY_TRANSACTION_FILENAME = "SAMPLE_TRANSACTION_DATA_FILENAME"
    }
}
