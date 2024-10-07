package com.example.kotlin.examenmoviles.framework.views

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.kotlin.examenmoviles.R
import com.example.kotlin.examenmoviles.databinding.ActivityMainBinding
import com.example.kotlin.examenmoviles.framework.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()
        //initializeObservers()
        //initializeListeners()


        // viewModel.get...

    }

    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

//    private fun initializeObservers() {
//        viewModel.data.observe(this) { data ->
//            data?.let {
//                setupRecyclerView(it.parameter)
//            }
//        }
//    }

//    private fun setupRecyclerView(data: List<String>) {
//        // Configurar el RecyclerView y el adaptador
//    }

}