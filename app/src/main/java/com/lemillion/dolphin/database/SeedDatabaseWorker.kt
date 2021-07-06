package com.lemillion.dolphin.database

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.lemillion.dolphin.entity.UnenrichedTransaction
import com.lemillion.dolphin.utilities.LocalDateTypeAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val filename = inputData.getString(KEY_FILENAME)
        try {
            if (filename != null) {
                val unenrichedTransactions = readDataFromFile(filename)
                val database = AppDatabase.getInstance(applicationContext)
                Log.i(TAG, "Inserting initial transactions")
                database.getUnenrichedTransactionDao().insertAll(unenrichedTransactions)
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

    private fun readDataFromFile(filename: String): List<UnenrichedTransaction> {
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
        const val KEY_FILENAME = "SAMPLE_ACCOUNT_DATA_FILENAME"
    }
}
