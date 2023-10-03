package com.example.borutoapp.presentation.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.use_cases.BorutoUseCases
import com.example.borutoapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val useCases: BorutoUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedHero: MutableState<Hero?> = mutableStateOf(null)
    val selectedHero: State<Hero?> =_selectedHero

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val heroId = savedStateHandle.get<Int>(Constants.DETAILS_ARGUMENT_KEY)
            _selectedHero.value = heroId?.let { useCases.getSelectedHeroUseCases(heroId = it) }
            _selectedHero.value?.name.let { Timber.d("DetailsViewModel -- Hero name is $it") }
        }
    }

}