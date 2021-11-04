package com.lemillion.minke.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
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
import com.lemillion.minke.viewmodel.AccountListViewModel
import com.lemillion.minke.viewmodel.TransactionListViewModel

@Composable
fun BottomNavigationView(
    accountListViewModel: AccountListViewModel = viewModel(),
    transactionListViewModel: TransactionListViewModel = viewModel()
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
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
fun BottomNavigationBar(
    navController: NavHostController
) {

    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        views.forEach { view ->
            BottomNavigationItem(
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
        composable(View.AccountsView.route) { AccountsView(accountListViewModel) }
        composable(View.TransactionsView.route) { TransactionsView(transactionListViewModel) }
    }
}