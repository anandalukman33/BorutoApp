package com.example.borutoapp.domain.use_cases.get_selected_hero

import com.example.borutoapp.data.repository.BorutoRepository
import com.example.borutoapp.domain.model.Hero

class GetSelectedHeroUseCases(
    private val borutoRepository: BorutoRepository
) {
    suspend operator fun invoke(heroId: Int): Hero {
        return borutoRepository.getSelectedHero(heroId = heroId)
    }
}