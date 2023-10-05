package com.example.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.borutoapp.presentation.screens.search.component.SearchWidget
import com.example.borutoapp.util.TestConstants
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun openSearchWidget_addInputText_assertInputText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = { text.value = it },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }

        val performTextInput = "Ananda Lukman"
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .performTextInput(performTextInput)
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .assertTextEquals(performTextInput)
    }

    @Test
    fun openSearchWidget_PerformInputText_AssertIconSearchExist_clickArrowBack_AssertSearchWidgetDoesNotExist() {
        val text = mutableStateOf("")
        val onBackIconPressed = mutableStateOf(true)

        composeTestRule.setContent {
            if (onBackIconPressed.value) {
                SearchWidget(
                    text = text.value,
                    onTextChange = { text.value = it },
                    onSearchClicked = {},
                    onCloseClicked = { onBackIconPressed.value = false }
                )
            }
        }


        val performTextInput = "Ananda Lukman"

        // Section is Exist or Not for Icon Search and Back when condition text input is Empty
        composeTestRule
            .onNodeWithContentDescription(TestConstants.LEADING_ICON_SEARCH)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithContentDescription(TestConstants.LEADING_ICON_BACK)
            .assertIsDisplayed()

        // Section Perform Text Input
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .performTextInput(performTextInput)
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .assertTextEquals(performTextInput)

        // Assert Icon Search is Displayed
        composeTestRule
            .onNodeWithContentDescription(TestConstants.LEADING_ICON_SEARCH)
            .assertIsDisplayed()
        composeTestRule
            .onNodeWithContentDescription(TestConstants.LEADING_ICON_BACK)
            .assertDoesNotExist()

        // Perform Click Trailing Icon Close for Clear Text
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TRAILING_ICON_BUTTON_CLOSE)
            .performClick()
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .assertTextContains("")

        // Assert Icon Back is Displayed
        composeTestRule
            .onNodeWithContentDescription(TestConstants.LEADING_ICON_SEARCH)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithContentDescription(TestConstants.LEADING_ICON_BACK)
            .assertIsDisplayed()

        // Perform Click Back with Assert Search Widget is Not Exist
        composeTestRule
            .onNodeWithContentDescription(TestConstants.LEADING_ICON_BACK)
            .performClick()

        composeTestRule
            .onNodeWithContentDescription(TestConstants.SEARCH_WIDGET)
            .assertDoesNotExist()

    }

    @Test
    fun openSearchWidget_addInputText_assertButtonNotVisible_pressClosedButton_assertEmptyText() {
        val text = mutableStateOf("")
        composeTestRule.setContent {
            SearchWidget(
                text = text.value,
                onTextChange = { text.value = it },
                onCloseClicked = {},
                onSearchClicked = {}
            )
        }

        val performTextInput = "Ananda Lukman"
        // Section Check Button Close is Not Displayed
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TRAILING_ICON_BUTTON_CLOSE)
            .assertDoesNotExist()

        // Section Input Text
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .performTextInput(performTextInput)

        // Section Check Button Close is Displayed
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TRAILING_ICON_BUTTON_CLOSE)
            .assertIsDisplayed()

        // Section Check Text Input is Equals with performTextInput Variable
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .assertTextEquals(performTextInput)

        // Section Close Button
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TRAILING_ICON_BUTTON_CLOSE)
            .performClick()

        // Section Check Text Input is Empty
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TEXT_FIELD)
            .assertTextContains("")

        // Section Check Button Close is Not Displayed when text input cleared by close icon
        composeTestRule
            .onNodeWithContentDescription(TestConstants.TRAILING_ICON_BUTTON_CLOSE)
            .assertDoesNotExist()
    }
}