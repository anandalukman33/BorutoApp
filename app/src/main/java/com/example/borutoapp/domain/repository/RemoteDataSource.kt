package com.example.borutoapp.domain.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroesData() : Flow<PagingData<Hero>>

    fun searchHeroesData(query: String) : Flow<PagingData<Hero>>
}