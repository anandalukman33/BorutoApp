package com.example.borutoapp.presentation.screens.home

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.borutoapp.presentation.screens.home.componen.topbar.HomeScreenTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar(onSearchClicked = {})
        }
    ) { _ ->

    }
}