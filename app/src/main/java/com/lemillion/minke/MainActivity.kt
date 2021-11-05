package com.lemillion.minke

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import com.lemillion.minke.ui.theme.MinkeTheme
import com.lemillion.minke.ui.view.NavigationView
import com.lemillion.minke.viewmodel.AccountListViewModel
import com.lemillion.minke.viewmodel.TransactionListViewModel
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val accountListViewModel: AccountListViewModel by viewModels()
    private val transactionListViewModel: TransactionListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinkeTheme {
                NavigationView(accountListViewModel, transactionListViewModel)
            }
        }
    }
}