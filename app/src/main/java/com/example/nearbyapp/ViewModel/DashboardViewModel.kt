package com.example.nearbyapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.nearbyapp.Domain.BannerModel
import com.example.nearbyapp.Domain.CategoryModel

class DashboardViewModel {
    private val repository = DashboardViewModel()

    fun loadCategory():LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBanners():LiveData<MutableList<BannerModel>> {
        return repository.loadBanners()
    }
}