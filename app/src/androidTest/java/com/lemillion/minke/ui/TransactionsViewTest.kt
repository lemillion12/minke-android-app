package com.lemillion.minke.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.lemillion.minke.data.entity.Transaction
import com.lemillion.minke.data.entity.TransactionDirection
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate
import java.util.*

class TransactionsViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAccountListItem() {
        // Start the app
        val transaction = getTestTransaction()
        val transactions = listOf(transaction)
        composeTestRule.setContent {
            TransactionList(transactions)
        }

        composeTestRule.onNode(hasText(transaction.toString())).assertIsDisplayed()
    }

    private fun getTestTransaction() = Transaction(
        1,
        LocalDate.now(),
        TransactionDirection.CREDIT,
        10.0,
        Currency.getInstance("USD"),
        LocalDate.now(),
        "123456789",
        "Test Description"
    )
}