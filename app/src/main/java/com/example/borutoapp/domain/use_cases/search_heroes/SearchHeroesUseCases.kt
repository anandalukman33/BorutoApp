package com.example.borutoapp.domain.use_cases.search_heroes

import androidx.paging.PagingData
import com.example.borutoapp.data.repository.BorutoRepository
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCases(
    private val borutoRepository: BorutoRepository
) {
    operator fun invoke(query: String): Flow<PagingData<Hero>> = borutoRepository.searchHeroes(query = query)
}