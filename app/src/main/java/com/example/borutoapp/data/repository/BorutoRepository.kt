package com.example.borutoapp.data.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.DataStoreOperations
import com.example.borutoapp.domain.repository.LocalDataSource
import com.example.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BorutoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations,
    private val localDataSource: LocalDataSource
) {

    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.saveOnBoardingState(isCompleted = isCompleted)
    }

    suspend fun getSelectedHero(heroId: Int): Hero = localDataSource.getSelectedHero(heroId)

    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()

    fun getAllHeroes(): Flow<PagingData<Hero>> = remoteDataSource.getAllHeroesData()

    fun searchHeroes(query: String): Flow<PagingData<Hero>> = remoteDataSource.searchHeroesData(query = query)

}