package com.example.borutoapp.domain.use_cases.read_on_boarding

import com.example.borutoapp.data.repository.BorutoRepository
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingUseCases(private val repository: BorutoRepository) {
    operator fun invoke(): Flow<Boolean> = repository.readOnBoardingState()
}