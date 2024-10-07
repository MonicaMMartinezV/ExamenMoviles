package com.example.kotlin.examenmoviles.framework.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.examenmoviles.data.DGBallRepository
import com.example.kotlin.examenmoviles.databinding.ActivityMainBinding
import com.example.kotlin.examenmoviles.framework.adapters.CharacterAdapter
import com.example.kotlin.examenmoviles.framework.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        setupRecyclerView()

        // Crear el repositorio que necesitas para el ViewModel
        val repository = DGBallRepository()
        val viewModelFactory = ViewModelFactory(repository)

        // Inicializar el ViewModel usando el ViewModelFactory
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        initializeObservers()
        initializeListeners()

        // Llamar al ViewModel para obtener los personajes de la primera página
        viewModel.fetchCharacters(1, 10)
    }


    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupRecyclerView() {
        // Inicializar el adaptador
        characterAdapter = CharacterAdapter(mutableListOf()) { character ->
            // Acción al hacer clic en un personaje, por ejemplo, mostrar detalles
            Toast.makeText(this, "Clicked on: ${character.name}", Toast.LENGTH_SHORT).show()
        }

        // Configurar el RecyclerView
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = characterAdapter
        }

        // Configurar la paginación para cargar más personajes cuando lleguemos al final del RecyclerView
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == characterAdapter.itemCount - 1) {
                    // Cargar más personajes cuando lleguemos al final
                    viewModel.fetchNextPage()
                }
            }
        })
    }

    val repository = DGBallRepository()  // Asegúrate de tener un constructor sin parámetros o inicialización adecuada


    private fun initializeObservers() {
        // Observar los personajes desde el ViewModel y actualizar el RecyclerView
        viewModel.characters.observe(this) { characterObject ->
            characterObject?.let {
                if (viewModel.isFirstPage()) {
                    characterAdapter.updateCharacters(it.items) // Actualizar personajes en la primera página
                } else {
                    characterAdapter.addCharacters(it.items) // Agregar personajes para paginación
                }
            }
        }

        // Observar errores, si es necesario
        viewModel.error.observe(this) { error ->
            error?.let {
                // Mostrar un mensaje de error, un Toast por ejemplo
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeListeners() {
        // Configurar el SearchView para filtrar personajes
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.filterCharacters(it)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty()) {
                        viewModel.filterCharacters(it)
                    } else {
                        // Si la búsqueda está vacía, volvemos a cargar los personajes de la primera página
                        viewModel.fetchCharacters(1, 10)
                    }
                }
                return true
                }
        })
}}