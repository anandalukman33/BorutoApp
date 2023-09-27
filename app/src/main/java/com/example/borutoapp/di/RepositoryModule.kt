package com.example.borutoapp.di

import android.content.Context
import com.example.borutoapp.data.repository.BorutoRepository
import com.example.borutoapp.data.repository.DataStoreOperationsImpl
import com.example.borutoapp.domain.repository.DataStoreOperations
import com.example.borutoapp.domain.use_cases.BorutoUseCases
import com.example.borutoapp.domain.use_cases.read_on_boarding.ReadOnBoardingUseCases
import com.example.borutoapp.domain.use_cases.save_on_boarding.SaveOnBoardingUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ): DataStoreOperations = DataStoreOperationsImpl(context = context)


    @Provides
    @Singleton
    fun provideUseCases(repository: BorutoRepository) : BorutoUseCases =
        BorutoUseCases(
            saveOnBoardingUseCases = SaveOnBoardingUseCases(repository),
            readOnBoardingUseCases = ReadOnBoardingUseCases(repository)
        )

}