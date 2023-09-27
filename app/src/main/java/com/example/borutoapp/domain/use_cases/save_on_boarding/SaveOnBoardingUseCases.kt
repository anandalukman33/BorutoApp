package com.example.borutoapp.domain.use_cases.save_on_boarding

import com.example.borutoapp.data.repository.BorutoRepository

class SaveOnBoardingUseCases(private val repository: BorutoRepository) {

    suspend operator fun invoke(isCompleted: Boolean) {
        repository.saveOnBoardingState(isCompleted = isCompleted)
    }
}