package com.example.kotlin.examenmoviles.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.examenmoviles.data.DGBallRepository
import com.example.kotlin.examenmoviles.data.network.model.CharacterBase
import example.kotlin.examenmoviles.data.network.model.CharacterObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: DGBallRepository) : ViewModel() {

    private val _characters = MutableLiveData<CharacterObject>()
    val characters: LiveData<CharacterObject> = _characters

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private var currentPage = 1
    private var allCharacters = mutableListOf<CharacterBase>() // Lista completa de personajes

    // Función que obtiene los personajes desde la API
    fun fetchCharacters(page: Int = 1, limit: Int = 10) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getAllCharacters(page, limit)
                response?.let {
                    // Si es la primera página, limpiamos la lista y reiniciamos el contador de página
                    if (page == 1) {
                        allCharacters.clear()
                        currentPage = 1
                    }

                    allCharacters.addAll(it.items) // Añadimos los nuevos personajes a la lista completa
                    _characters.postValue(it) // Publicamos los personajes en el LiveData

                    currentPage = page // Actualizamos la página actual
                } ?: run {
                    _error.postValue("Error fetching characters")
                }
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
        }
    }

    // Función para cargar la siguiente página de personajes
    fun fetchNextPage(limit: Int = 10) {
        fetchCharacters(currentPage + 1, limit)
    }

    // Función para filtrar los personajes por nombre
    fun filterCharacters(query: String) {
        val filteredList = allCharacters.filter {
            it.name.contains(query, ignoreCase = true)
        }
        _characters.postValue(CharacterObject(filteredList, meta = null, links = null))
    }

    // Indica si estamos en la primera página (para reiniciar la lista)
    fun isFirstPage(): Boolean = currentPage == 1
}
