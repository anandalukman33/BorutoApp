package com.example.borutoapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.presentation.attribute.RatingWidget
import com.example.borutoapp.presentation.screens.home.component.topbar.HomeScreenTopBar
import com.example.borutoapp.ui.theme.LARGE_PADDING

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeScreenTopBar(onSearchClicked = {})
        }
    ) {
        RatingWidget(
            modifier = Modifier.padding(all = LARGE_PADDING),
            rating = 5.0
        )
    }
}