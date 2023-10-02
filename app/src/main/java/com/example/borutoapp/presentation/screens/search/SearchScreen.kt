package com.example.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.borutoapp.presentation.screens.home.component.content.ListContent
import com.example.borutoapp.presentation.screens.search.component.SearchTopBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            SearchTopBar(
                searchQuery,
                onTextChange = {
                    searchViewModel.updateSearchQuery(query = it)
                    searchViewModel.searchHeroes(query = it)
                },
                onSearchClicked = { searchViewModel.searchHeroes(query = it) },
                onCloseClicked = { navHostController.popBackStack() }
            )
        },
        content = {
            ListContent(
                heroes = heroes,
                navHostController = navHostController,
                scaffoldState = scaffoldState
            )
        }
    )
}