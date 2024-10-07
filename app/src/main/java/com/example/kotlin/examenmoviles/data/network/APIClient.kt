package com.example.kotlin.examenmoviles.data.network

class APIClient {
    private lateinit var api: APIService

    suspend fun getSomething(parameter: String): String? {
        api = NetworkModuleDI()
        return try {
            api.getSomething(parameter)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}