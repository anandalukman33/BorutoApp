package com.example.borutoapp.presentation.screens.attribute

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import com.example.borutoapp.presentation.attribute.RatingWidget
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.util.TestConstants
import org.junit.Rule
import org.junit.Test

class RatingWidgetTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun passZeroPointZeroValue_Assert_FiveEmptyStars() {
        composeTestRule.setContent { 
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.0
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(5)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(0)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(0)
    }


    @Test
    fun passZeroPointFiveValue_Assert_OneHalfStar_And_FourEmptyStar() {
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 0.5
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(4)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(1)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(0)
    }


    @Test
    fun passTwoPointFiveValue_Assert_TwoFilledStars_OneHalfStar_And_Two_EmptyStar() {
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 2.5
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(2)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(1)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(2)
    }


    @Test
    fun passFivePointZeroValue_Assert_FiveFilledStars() {
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 5.0
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(0)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(0)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(5)
    }

    @Test
    fun passNegativeOnePointZeroValue_Assert_FiveEmptyStars() {
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = -1.0
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(5)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(0)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(0)
    }

    @Test
    fun passInvalidLastNumberValue_Assert_FiveFilledStars() {
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 5.5
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(0)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(0)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(5)
    }

    @Test
    fun passInvalidFirstNumberValue_Assert_FiveFilledStars() {
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 7.5
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(0)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(0)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(5)
    }

    @Test
    fun passInvalidFirstAndLastNumberValue_Assert_FiveFilledStars() {
        composeTestRule.setContent {
            RatingWidget(
                modifier = Modifier.padding(all = SMALL_PADDING),
                rating = 9.9
            )
        }

        // Assert Empty Star is 5
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.EMPTY_STAR)
            .assertCountEquals(0)

        // Assert Half Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.HALF_STAR)
            .assertCountEquals(0)

        // Assert Filled Star is 0
        composeTestRule
            .onAllNodesWithContentDescription(TestConstants.FILLED_STAR)
            .assertCountEquals(5)
    }

}