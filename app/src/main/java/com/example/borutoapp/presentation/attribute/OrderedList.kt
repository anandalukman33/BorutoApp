package com.example.borutoapp.presentation.attribute

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.titleColor

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color
) {
    Column {
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold
        )

        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(ContentAlpha.medium),
                text = "${index.plus(1)}. $item",
                color = textColor,
                fontSize = MaterialTheme.typography.body1.fontSize,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OrderedListDarkPreview() {
    OrderedList(
        title = "Family",
        items = listOf(
            "Uzumaki Naruto",
            "Uzumaki Hinata",
            "Uzumaki Boruto",
            "Uzumaki Himawari",
            "Uzumaki Minato",
            "Uzumaki Kushina"
        ),
        textColor = MaterialTheme.colors.titleColor
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Composable
fun OrderedListLightPreview() {
    OrderedList(
        title = "Family",
        items = listOf(
            "Uzumaki Naruto",
            "Uzumaki Hinata",
            "Uzumaki Boruto",
            "Uzumaki Himawari",
            "Uzumaki Minato",
            "Uzumaki Kushina"
        ),
        textColor = MaterialTheme.colors.titleColor
    )
}