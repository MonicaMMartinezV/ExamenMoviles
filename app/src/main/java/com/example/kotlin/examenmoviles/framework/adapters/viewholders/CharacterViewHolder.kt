package com.example.kotlin.examenmoviles.framework.adapters.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.examenmoviles.R
import com.example.kotlin.examenmoviles.data.network.model.CharacterBase

class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val characterName: TextView = itemView.findViewById(R.id.characterName)
    private val characterSpecies: TextView = itemView.findViewById(R.id.characterSpecies)
    private val characterImage: ImageView = itemView.findViewById(R.id.characterImage)

    fun bind(character: CharacterBase) {
        // Vinculamos los datos del personaje a las vistas del layout
        characterName.text = character.name
        characterSpecies.text = character.description

        // Cargamos la imagen del personaje usando Glide
        Glide.with(itemView.context)
            .load(character.imageUrl)
            .into(characterImage)
    }
}
