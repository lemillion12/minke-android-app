package com.lemillion.minke

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import com.lemillion.minke.ui.BottomNavigationView
import com.lemillion.minke.viewmodel.AccountListViewModel
import com.lemillion.minke.viewmodel.TransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val accountListViewModel: AccountListViewModel by viewModels()
    private val transactionListViewModel: TransactionListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                BottomNavigationView(accountListViewModel, transactionListViewModel)
            }
        }
    }
}