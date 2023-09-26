package com.example.borutoapp.presentation.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.borutoapp.domain.model.OnBoardingPage
import com.example.borutoapp.ui.theme.welcomeScreenBackgroundColor
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    val page = listOf(OnBoardingPage.First, OnBoardingPage.Second, OnBoardingPage.Third)

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.welcomeScreenBackgroundColor)
    ) {
        HorizontalPager(
            count = page.size,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) {
            PagerScreen(onBoardingPage = page[it])
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    

}