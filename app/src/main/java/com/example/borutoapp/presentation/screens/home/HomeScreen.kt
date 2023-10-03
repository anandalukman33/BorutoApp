package com.example.borutoapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.presentation.screens.home.component.content.ListContent
import com.example.borutoapp.presentation.screens.home.component.topbar.HomeScreenTopBar
import com.example.borutoapp.ui.theme.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()
    val scaffoldState = rememberScaffoldState()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            HomeScreenTopBar(onSearchClicked = { navHostController.navigate(Screen.Search.route) })
        },
        content = {
            ListContent(heroes = allHeroes, navHostController = navHostController, scaffoldState = scaffoldState)
        }
    )
}