package com.lemillion.minke.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lemillion.minke.data.entity.Transaction
import com.lemillion.minke.viewmodel.TransactionListViewModel

@Composable
fun TransactionsView(
    transactionListViewModel: TransactionListViewModel = viewModel(),
    accountId: Long?
) {
    val transactions by transactionListViewModel.transactions.observeAsState(initial = emptyList())
    accountId?.let {
        TransactionList(transactions.filter { transaction -> accountId == transaction.accountId })
    } ?: TransactionList(transactions)
}

@Composable
fun TransactionList(transactions: List<Transaction>) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = transactions,
            key = { transaction ->
                // Return a stable + unique key for the item
                transaction.id
            }
        ) { transaction ->
            TransactionListItem(transaction)
        }
    }
}

@Composable
fun TransactionListItem(transaction: Transaction) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(transaction.toString())
            }
        }
    }

}