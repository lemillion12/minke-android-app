package com.lemillion.minke.ui.view

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.lemillion.minke.viewmodel.AccountListViewModel
import com.lemillion.minke.viewmodel.TransactionListViewModel

val views = listOf(
    View.AccountsView,
    View.TransactionsView,
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
                selected = currentDestination?.hierarchy?.any { it.route == view.route } == true,
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
                }
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
        startDestination = View.AccountsView.route,
        modifier = Modifier.padding(padding)
    ) {
        composable(View.AccountsView.route) {
            AccountsView(navController, accountListViewModel)
        }
        composable(
            View.TransactionsView.accountIdRoute("{accountId}"),
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