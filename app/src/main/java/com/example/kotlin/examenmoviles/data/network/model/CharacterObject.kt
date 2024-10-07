package example.kotlin.examenmoviles.data.network.model

import com.google.gson.annotations.SerializedName
import com.example.kotlin.examenmoviles.data.network.model.CharacterBase

data class CharacterObject(
    @SerializedName("items") val items: List<CharacterBase>,   // Lista de personajes
    @SerializedName("meta") val meta: Meta?,                   // Información de paginación
    @SerializedName("links") val links: Links?               // Enlaces de paginación (first, next, previous, last)
)

data class Meta(
    @SerializedName("totalItems") val totalItems: Int,      // Número total de personajes
    @SerializedName("itemCount") val itemCount: Int,       // Número de personajes en esta página
    @SerializedName("itemsPerPage") val itemsPerPage: Int, // Número de personajes por página
    @SerializedName("totalPages") val totalPages: Int,     // Total de páginas disponibles
    @SerializedName("currentPage") val currentPage: Int    // Página actual
)

data class Links(
    @SerializedName("first") val first: String,            // Enlace a la primera página
    @SerializedName("previous") val previous: String?,     // Enlace a la página anterior (puede ser null)
    @SerializedName("next") val next: String?,             // Enlace a la página siguiente (puede ser null)
    @SerializedName("last") val last: String               // Enlace a la última página
)
