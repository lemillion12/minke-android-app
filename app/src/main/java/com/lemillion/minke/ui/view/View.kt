package com.lemillion.minke.ui.view

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Payments
import androidx.compose.ui.graphics.vector.ImageVector
import com.lemillion.minke.R

sealed class View(val route: String, @StringRes val label: Int, val icon: ImageVector) {
    object AccountsView :
        View("accounts", R.string.accounts, Icons.Filled.AccountBalance)

    object TransactionsView :
        View("transactions", R.string.transactions, Icons.Filled.Payments) {
        fun accountIdRoute(accountId: Any): String {
            return this.route.plus("?accountId=").plus(accountId)
        }
    }
}