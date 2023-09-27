package com.example.borutoapp.domain.use_cases

import com.example.borutoapp.domain.use_cases.get_all_heroes.GetAllHeroesUseCases
import com.example.borutoapp.domain.use_cases.read_on_boarding.ReadOnBoardingUseCases
import com.example.borutoapp.domain.use_cases.save_on_boarding.SaveOnBoardingUseCases

data class BorutoUseCases (
    val saveOnBoardingUseCases: SaveOnBoardingUseCases,
    val readOnBoardingUseCases: ReadOnBoardingUseCases,
    val getAllHeroesUseCases: GetAllHeroesUseCases
)