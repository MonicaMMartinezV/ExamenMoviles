package com.example.kotlin.examenmoviles.data.network

import example.kotlin.examenmoviles.data.network.model.CharacterObject
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DGBallAPIService {

    // Obtener todos los personajes (GET /characters)
    @GET("characters")
    @Headers("Content-Type: application/json")
    suspend fun getAllCharacters(
        @Query("page") page: Int = 1,  // Paginación
        @Query("limit") limit: Int = 10 // Límite de elementos por página
    ): CharacterObject

    // Obtener información de paginación (GET /characters?page=X&limit=Y)
    @GET("characters")
    @Headers("Content-Type: application/json")
    suspend fun getPaginationInfo(
        @Query("page") page: Int,   // Página específica para la información de paginación
        @Query("limit") limit: Int  // Límite de elementos por página
    ): CharacterObject
}