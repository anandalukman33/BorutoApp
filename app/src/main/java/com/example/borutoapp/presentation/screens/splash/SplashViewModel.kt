package com.example.borutoapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.borutoapp.domain.use_cases.BorutoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val useCases: BorutoUseCases
) : ViewModel() {

    private val isOnBoardingCompleted = MutableStateFlow(false)
    val onBoardingCompleted: StateFlow<Boolean> = isOnBoardingCompleted


    init {
        viewModelScope.launch(Dispatchers.IO) {
            isOnBoardingCompleted.value = useCases.readOnBoardingUseCases().stateIn(viewModelScope).value
        }
    }

}