package com.lemillion.minke.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Payments
import androidx.compose.ui.graphics.vector.ImageVector
import com.lemillion.minke.R

sealed class View(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object AccountsView : View("accounts", R.string.accounts, Icons.Filled.AccountBalance)
    object TransactionsView :
        View("transactions", R.string.transactions, Icons.Filled.Payments)
}

val views = listOf(
    View.AccountsView,
    View.TransactionsView,
)