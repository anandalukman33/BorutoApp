package com.example.borutoapp.domain.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.example.borutoapp.data.repository.BorutoRepository
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCases(
    private val repository: BorutoRepository
) {
    operator fun invoke(): Flow<PagingData<Hero>> = repository.getAllHeroes()
}