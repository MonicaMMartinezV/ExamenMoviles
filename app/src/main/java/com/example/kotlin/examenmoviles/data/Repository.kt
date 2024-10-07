package com.example.kotlin.examenmoviles.data

import com.example.kotlin.examenmoviles.data.network.APIClient

class Repository {
    private val api = APIClient()

    suspend fun getSomething(parameter: String) = api.getSomething(parameter)
}