package com.example.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.borutoapp.presentation.screens.search.component.SearchTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            SearchTopBar("", {}, {}, {})
        }
    ) { }
}