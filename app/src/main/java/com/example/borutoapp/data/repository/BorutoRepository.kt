package com.example.borutoapp.data.repository

import androidx.paging.PagingData
import com.example.borutoapp.domain.model.Hero
import com.example.borutoapp.domain.repository.DataStoreOperations
import com.example.borutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BorutoRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataStore: DataStoreOperations
) {

    suspend fun saveOnBoardingState(isCompleted: Boolean) {
        dataStore.saveOnBoardingState(isCompleted = isCompleted)
    }

    fun readOnBoardingState(): Flow<Boolean> = dataStore.readOnBoardingState()

    fun getAllHeroes(): Flow<PagingData<Hero>> = remoteDataSource.getAllHeroesData()

}