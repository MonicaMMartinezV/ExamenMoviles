package com.example.kotlin.examenmoviles.data

import com.example.kotlin.examenmoviles.data.network.DGBallAPIClient

class DGBallRepository {
    private val api = DGBallAPIClient()

    // Obtener todos los personajes
    suspend fun getAllCharacters(page: Int, limit: Int) = api.getAllCharacters(page, limit)

    // Obtener la información de paginación
    suspend fun getPaginationInfo(page: Int, limit: Int) = api.getPaginationInfo(page, limit)
}
