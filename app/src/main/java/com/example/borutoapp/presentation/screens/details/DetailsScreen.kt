package com.example.borutoapp.presentation.screens.details

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import com.example.borutoapp.util.Constants
import com.example.borutoapp.util.PaletteGenerator
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun DetailsScreen(
    navHostController: NavHostController,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val selectedHero by detailsViewModel.selectedHero.collectAsState()
    val colorPalette by detailsViewModel.colorPalette

    if (colorPalette.isNotEmpty()) {
        DetailsContent(navHostController = navHostController, selectedHero = selectedHero, colors = colorPalette)
    } else {
        detailsViewModel.generateColorPalette()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        detailsViewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.GeneratorColorPalette -> {
                    val bitmap = PaletteGenerator.convertUrlImageToBitmap(
                        imageUrl = "${Constants.BASE_URL}${selectedHero?.image}",
                        context = context
                    )
                    if (bitmap != null) {
                        detailsViewModel.setColorPalette(
                            color = PaletteGenerator.extractColorFromBitmap(bitmap = bitmap)
                        )
                    }
                }
            }
        }
    }

}