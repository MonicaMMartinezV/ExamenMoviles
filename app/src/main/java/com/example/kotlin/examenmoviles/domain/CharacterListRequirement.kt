package com.example.kotlin.examenmoviles.domain

import com.example.kotlin.examenmoviles.data.DGBallRepository
import example.kotlin.examenmoviles.data.network.model.CharacterObject

class CharacterListRequirement(private val repository: DGBallRepository) {
    // Función suspendida para invocar el requirement y obtener los personajes
    suspend operator fun invoke(
        page: Int = 1,     // Número de página
        limit: Int = 10    // Límite de personajes por página
    ): CharacterObject? {
        return repository.getAllCharacters(page, limit)
    }
}
