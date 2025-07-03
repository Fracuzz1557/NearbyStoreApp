package com.example.nearbyapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.nearbyapp.Domain.CategoryModel
import com.example.nearbyapp.Domain.StoreModel
import com.example.nearbyapp.Repository.ResultsRepository

class ResultsViewModel {
    private val repository=ResultsRepository()

    fun loadSubCategory(id:String):LiveData<MutableList<CategoryModel>>{
        return repository.LoadSubCategory(id)
    }

    fun loadPopular(id:String):LiveData<MutableList<StoreModel>>{
        return repository.LoadPopular(id)
    }

    fun loadNearest(id:String):LiveData<MutableList<StoreModel>>{
        return repository.LoadNearest(id)
    }
}