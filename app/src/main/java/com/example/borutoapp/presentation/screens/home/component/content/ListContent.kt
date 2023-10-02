package com.example.borutoapp.presentation.screens.home.component.content

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.borutoapp.R
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.presentation.attribute.RatingWidget
import com.example.borutoapp.presentation.attribute.ShimmerEffect
import com.example.borutoapp.ui.theme.HERO_ITEM_HEIGHT
import com.example.borutoapp.ui.theme.LARGE_PADDING
import com.example.borutoapp.ui.theme.MEDIUM_PADDING
import com.example.borutoapp.ui.theme.SMALL_PADDING
import com.example.borutoapp.ui.theme.topAppBarContentColor
import com.example.borutoapp.util.Constants
import kotlinx.coroutines.launch

@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navHostController: NavHostController,
    scaffoldState: ScaffoldState
) {
    val result = handlePagingResult(heroes = heroes)
    val coroutineScope = rememberCoroutineScope()

    val showSnackBar: (
        message: String?,
        actionLabel: String,
        actionPerformed: () -> Unit,
        dismissed: () -> Unit
    ) -> Unit = { message, actionLabel, actionPerformed, dismissed ->
        coroutineScope.launch {
            val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                message = message.toString(),
                actionLabel = actionLabel
            )
            when (snackBarResult) {
                SnackbarResult.ActionPerformed -> actionPerformed.invoke()
                SnackbarResult.Dismissed -> dismissed.invoke()
            }
        }
    }

    when {

        result.containsKey("loadStateNull") -> {
            LazyColumn(
                contentPadding = PaddingValues(all = SMALL_PADDING),
                verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
            ) {
                items(
                    count = heroes.itemCount,
                    key = heroes.itemKey(key = { hero -> hero.id }),
                    contentType = heroes.itemContentType()
                ) { index ->
                    val item = heroes[index]
                    item?.let {
                        HeroItem(hero = item, navHostController = navHostController)
                    }
                }
            }
        }

        result.containsKey("loadStateIsNotNull") -> {
            showSnackBar.invoke(
                result["loadStateIsNotNull"],
                "refresh",
                { heroes.refresh() },
                {}
            )
        }
    }
}

@Composable
fun handlePagingResult(heroes: LazyPagingItems<Hero>): Map<String, String?> {

    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                mapOf("isRefresh" to error?.error?.message.toString())
            }
            error != null -> {
                mapOf("loadStateIsNotNull" to error.error.message.toString())
            }
            else -> mapOf("loadStateNull" to null)
        }
    }
}

@Composable
fun HeroItem(
    hero: Hero,
    navHostController: NavHostController
) {

    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = "${Constants.BASE_URL}${hero.image}")
            .apply(block = {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }).build()
    )

    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT)
            .clickable {
                navHostController.navigate(Screen.Details.passHeroId(heroId = hero.id))
            },
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painter,
                contentDescription = stringResource(R.string.hero_image),
                contentScale = ContentScale.Crop
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING)
            ) {
                Text( // Section Name Hero Content
                    text = hero.name,
                    color = MaterialTheme.colors.topAppBarContentColor,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text( // Section Description Hero Content
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(ContentAlpha.medium),

                        )

                }
            }
        }
    }
}

@Preview
@Composable
fun HeroItemLightPreview() {
    HeroItem(
        hero =
        Hero(
            1,
            "Sasuke",
            "",
            "Sasuke adalah adiknya Uchiha Itachi... ",
            5.0,
            100,
            "",
            "",
            listOf(),
            listOf(),
            listOf()
        ), navHostController = rememberNavController()
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroItemDarkPreview() {
    HeroItem(
        hero =
        Hero(
            1,
            "Sasuke",
            "",
            "Sasuke adalah adiknya Uchiha Itachi... ",
            5.0,
            100,
            "",
            "",
            listOf(),
            listOf(),
            listOf()
        ), navHostController = rememberNavController()
    )
}