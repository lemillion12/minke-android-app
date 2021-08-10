package com.lemillion.minke

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lemillion.minke.adapter.TransactionViewAdapter
import com.lemillion.minke.data.entity.UnenrichedTransaction
import com.lemillion.minke.viewmodels.UnenrichedTransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var transactionViewAdapter: TransactionViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.rvTransactions)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val model: UnenrichedTransactionListViewModel by viewModels()
        model.unenrichedTransactions.observe(
            this,
            Observer<List<UnenrichedTransaction>> { unenrichedTransactions ->
                transactionViewAdapter =
                    TransactionViewAdapter(unenrichedTransactions.toTypedArray())
                recyclerView.adapter = transactionViewAdapter
            })
    }
}