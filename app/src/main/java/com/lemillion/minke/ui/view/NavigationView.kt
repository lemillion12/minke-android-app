package com.lemillion.minke.ui.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lemillion.android.fab.FabItem
import com.lemillion.android.fab.MultiFloatingActionButton
import com.lemillion.minke.viewmodel.AccountListViewModel
import com.lemillion.minke.viewmodel.TransactionListViewModel

val views = listOf(
    View.Accounts,
    View.Transactions,
)

@ExperimentalMaterialApi
@ExperimentalMaterial3Api
@Composable
fun NavigationView(
    accountListViewModel: AccountListViewModel = viewModel(),
    transactionListViewModel: TransactionListViewModel = viewModel()
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar(navController)
        },
        content = { padding ->
            NavHostContainer(
                navController = navController,
                padding = padding,
                accountListViewModel,
                transactionListViewModel
            )
        },
        floatingActionButton = {
            SpeedDial()
        }
    )
}

@Composable
fun NavigationBar(
    navController: NavHostController
) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        views.forEach { view ->
            NavigationBarItem(
                icon = { Icon(imageVector = view.icon, contentDescription = view.route) },
                label = { Text(stringResource(view.label)) },
                selected = currentDestination?.hierarchy?.any {
                    it.route?.startsWith(view.route) ?: false
                } ?: false,
                onClick = {
                    navController.navigate(view.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                alwaysShowLabel = true
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    accountListViewModel: AccountListViewModel = viewModel(),
    transactionListViewModel: TransactionListViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = View.Accounts.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(View.Accounts.route) {
            AccountsView(navController, accountListViewModel)
        }
        composable(
            View.Transactions.accountIdRoute("{accountId}"),
            arguments = listOf(navArgument("accountId") {
                nullable = true
            })
        ) { backStackEntry ->
            TransactionsView(
                transactionListViewModel,
                backStackEntry.arguments?.getString("accountId")?.toLong()
            )
        }
    }
}

@Composable
fun SpeedDial() {
    val context = LocalContext.current
    val items = listOf(
        FabItem(
            View.Accounts.icon,
            stringResource(View.Accounts.label)
        ) {
            makeToast(context, "Accounts FAB clicked! Adding Account logic not yet implemented")
        },
        FabItem(
            View.Transactions.icon,
            stringResource(View.Transactions.label)
        ) {
            makeToast(
                context,
                "Transactions FAB clicked! Adding Transaction logic not yet implemented"
            )
        }
    )
    MultiFloatingActionButton(
        fabIcon = Icons.Filled.Add,
        items = items,
        showLabels = true
    )
}

private fun makeToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}