package com.lemillion.dolphin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lemillion.dolphin.adapter.TransactionViewAdapter
import com.lemillion.dolphin.database.AppDatabase
import com.lemillion.dolphin.entity.UnenrichedTransaction
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    private lateinit var transactionViewAdapter: TransactionViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        setUpButton()
    }

    private fun setUpButton() {
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            setUpRecyclerView()
        }
    }

    private fun setUpRecyclerView() {
        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvTransactions)
        recyclerView.layoutManager = LinearLayoutManager(this)

        runBlocking {
            lifecycleScope.launch {
                val transactions = loadTransaction()
                transactionViewAdapter = TransactionViewAdapter(transactions.toTypedArray())
                recyclerView.adapter = transactionViewAdapter
            }
        }
    }

    private suspend fun loadTransaction(): List<UnenrichedTransaction> {
        // data to populate the RecyclerView with
        val database = AppDatabase.getInstance(applicationContext)
        val allTransactions = database.getUnenrichedTransactionDao().getAll()
        Log.i(TAG, "All transactions: $allTransactions")
        return allTransactions
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}