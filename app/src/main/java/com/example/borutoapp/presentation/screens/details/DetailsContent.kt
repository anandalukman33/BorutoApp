package com.example.borutoapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.presentation.attribute.InfoBox
import com.example.borutoapp.presentation.attribute.OrderedList
import com.example.borutoapp.ui.theme.INFO_ICON_SIZE
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.MIN_SHEET_HEIGHT
import com.example.borutoapp.ui.theme.titleColor

@ExperimentalMaterialApi
@Composable
fun DetailsContent(
    navHostController: NavHostController,
    selectedHero: Hero?
) {

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberBottomSheetState(
            initialValue = BottomSheetValue.Expanded
        )
    )

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = MIN_SHEET_HEIGHT,
        sheetContent = { selectedHero?.let { BottomSheetContent(selectedHero = it) } },
        content = {}
    )

}

@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colors.primary,
    sheetBackgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = MaterialTheme.colors.titleColor
) {
        Column(
            modifier = Modifier
                .background(sheetBackgroundColor)
                .padding(all = LARGE_PADDING)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = LARGE_PADDING),
                verticalAlignment = Alignment.CenterVertically
            ) {

                /**
                 * Section Name Of Hero
                 */
                Icon(
                    modifier = Modifier
                        .size(INFO_ICON_SIZE)
                        .weight(2f),
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = stringResource(R.string.logo_icon_bottom_sheet_content),
                    tint = contentColor
                )
                Text(
                    modifier = Modifier
                        .weight(8f),
                    text = selectedHero.name,
                    color = contentColor,
                    fontSize = MaterialTheme.typography.h4.fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MEDIUM_PADDING),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                /**
                 * Section Power Hero
                 */
                InfoBox(
                    icon = painterResource(id = R.drawable.ic_bolt),
                    iconColor = infoBoxIconColor,
                    bigText = "${selectedHero.power}",
                    smallText = stringResource(R.string.power),
                    textColor = contentColor
                )

                /**
                 * Section Month Birth Hero
                 */
                InfoBox(
                    icon = painterResource(id = R.drawable.ic_calendar),
                    iconColor = infoBoxIconColor,
                    bigText = selectedHero.month,
                    smallText = stringResource(R.string.month),
                    textColor = contentColor
                )

                /**
                 * Section Day Birth Hero
                 */
                InfoBox(
                    icon = painterResource(id = R.drawable.ic_cake),
                    iconColor = infoBoxIconColor,
                    bigText = selectedHero.day,
                    smallText = stringResource(R.string.birthday),
                    textColor = contentColor
                )
            }

            /**
             * Section Description About Hero
             */
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.about),
                color = contentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(ContentAlpha.medium)
                    .padding(bottom = MEDIUM_PADDING),
                text = selectedHero.about,
                color = contentColor,
                fontSize = MaterialTheme.typography.body1.fontSize,
                maxLines = 7,
                textAlign = TextAlign.Justify
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                /**
                 * Section Family of Hero
                 */
                OrderedList(
                    title = stringResource(R.string.family),
                    items = selectedHero.family,
                    textColor = contentColor
                )

                /**
                 * Section Abilities of Hero
                 */
                OrderedList(
                    title = stringResource(R.string.abilities),
                    items = selectedHero.abilities,
                    textColor = contentColor
                )

                /**
                 * Section Nature Types of Hero
                 */
                OrderedList(
                    title = stringResource(R.string.nature_types),
                    items = selectedHero.natureTypes,
                    textColor = contentColor
                )
            }
        }

}

@Preview(showBackground = true)
@Composable
fun BottomContentPreview() {
    BottomSheetContent(
        selectedHero = Hero(
            id = 1,
            name = "Naruto",
            image = "",
            about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            rating = 4.5,
            power = 0,
            month = "Oct",
            day = "1st",
            family = listOf("Minato", "Kushina", "Boruto", "Himawari"),
            abilities = listOf("Sage Mode", "Shadow Clone", "Rasengan"),
            natureTypes = listOf("Earth", "Wind")
        )
    )
}