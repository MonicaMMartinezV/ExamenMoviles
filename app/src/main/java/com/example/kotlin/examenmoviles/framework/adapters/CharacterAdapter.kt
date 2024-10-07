package com.example.kotlin.examenmoviles.framework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examenmoviles.R
import com.example.kotlin.examenmoviles.data.network.model.CharacterBase
import com.example.kotlin.examenmoviles.framework.adapters.viewholders.CharacterViewHolder

class CharacterAdapter(
    private val characters: MutableList<CharacterBase>,
    private val onCharacterClick: (CharacterBase) -> Unit // Callback para manejar clics en personajes
) : RecyclerView.Adapter<CharacterViewHolder>() {

    private var allCharacters: List<CharacterBase> = characters.toList() // Lista original de personajes para el filtro

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = characters[position]
        holder.bind(character)

        // Manejamos el clic en un personaje
        holder.itemView.setOnClickListener {
            onCharacterClick(character)
        }
    }

    override fun getItemCount(): Int = characters.size

    // Actualiza la lista de personajes al cargar la primera página o realizar un filtro
    fun updateCharacters(newCharacters: List<CharacterBase>) {
        characters.clear()
        characters.addAll(newCharacters)
        allCharacters = newCharacters.toList() // Actualizamos la lista completa para el filtro
        notifyDataSetChanged()
    }

    // Añade más personajes al final de la lista (paginación)
    fun addCharacters(newCharacters: List<CharacterBase>) {
        characters.addAll(newCharacters)
        allCharacters = characters.toList() // Actualizamos la lista completa para mantener el filtro
        notifyDataSetChanged()
    }

    // Filtra la lista de personajes por nombre
    fun filterCharacters(query: String) {
        val filteredList = allCharacters.filter {
            it.name.contains(query, ignoreCase = true)
        }
        characters.clear()
        characters.addAll(filteredList)
        notifyDataSetChanged()
    }
}