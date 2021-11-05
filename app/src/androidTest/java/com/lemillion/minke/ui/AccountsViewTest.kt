package com.lemillion.minke.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.lemillion.minke.data.entity.Account
import com.lemillion.minke.data.entity.AccountType
import com.lemillion.minke.ui.view.AccountList
import org.junit.Rule
import org.junit.Test

@ExperimentalMaterialApi
class AccountsViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAccountListItem() {
        // Start the app
        val account = getTestAccount()
        val accounts = listOf(account)
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        composeTestRule.setContent {
            AccountList(navController, accounts)
        }

        composeTestRule.onNode(hasText(account.toString())).assertIsDisplayed()
    }

    private fun getTestAccount() = Account(AccountType.SAVINGS, "Test Account", "123456789")
}