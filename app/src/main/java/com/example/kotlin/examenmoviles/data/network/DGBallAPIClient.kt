package com.example.kotlin.examenmoviles.data.network

import com.example.kotlin.examenmoviles.data.network.model.CharacterBase
import example.kotlin.examenmoviles.data.network.model.CharacterObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DGBallAPIClient {
    private lateinit var api: DGBallAPIService

    // Función suspendida para obtener todos los personajes
    suspend fun getAllCharacters(page: Int = 1, limit: Int = 10): CharacterObject? {
        return try {
            // Llamada suspendida a la API usando los parámetros de paginación
            api.getAllCharacters(page = page, limit = limit)
        } catch (e: Exception) {
            // En caso de error, imprimimos la traza y devolvemos null
            e.printStackTrace()
            null
        }
    }

    // Función suspendida para obtener la información de paginación (opcional si se necesita por separado)
    suspend fun getPaginationInfo(page: Int, limit: Int): CharacterObject? {
        return try {
            api.getPaginationInfo(page, limit)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
