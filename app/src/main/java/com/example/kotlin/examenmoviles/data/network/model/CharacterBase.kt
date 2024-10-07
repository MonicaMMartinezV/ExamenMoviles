package com.example.kotlin.examenmoviles.data.network.model

import com.google.gson.annotations.SerializedName

data class CharacterBase(
    @SerializedName("id") val id: Int,                // Identificador único del personaje
    @SerializedName("name") val name: String,         // Nombre del personaje
    @SerializedName("ki") val ki: String,   // Especie del personaje (ejemplo: Saiyan, Namekian)
    @SerializedName("maxKi") val maxKi: String,   // Especie del personaje (ejemplo: Saiyan, Namekian)
    @SerializedName("race") val race: String,  // Planeta de origen del personaje
    @SerializedName("description") val description: String,    // Descripción del personaje
    @SerializedName("image") val imageUrl: String           // URL de la imagen del personaje
)
