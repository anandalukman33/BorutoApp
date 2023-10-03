package com.example.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.use_cases.BorutoUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: BorutoUseCases
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery = _searchQuery

    private val _searchedHeroes = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
    val searchedHeroes = _searchedHeroes

    init {
        searchHeroes("")
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchHeroesUseCases(query = query).cachedIn(viewModelScope).collect {
                _searchedHeroes.value = it
            }
        }
    }

}