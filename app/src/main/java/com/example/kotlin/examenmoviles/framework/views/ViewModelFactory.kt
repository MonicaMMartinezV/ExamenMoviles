package com.example.kotlin.examenmoviles.framework.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.examenmoviles.data.DGBallRepository
import com.example.kotlin.examenmoviles.framework.viewmodel.MainViewModel

class ViewModelFactory(private val repository: DGBallRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

