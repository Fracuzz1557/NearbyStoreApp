package com.example.nearbyapp.ViewModel

import androidx.lifecycle.LiveData
import com.example.nearbyapp.Domain.BannerModel
import com.example.nearbyapp.Domain.CategoryModel
import com.example.nearbyapp.Repository.DashboardRepository

class DashboardViewModel {
    // ✅ CORREGIDO: Ahora usa DashboardRepository en vez de DashboardViewModel
    private val repository = DashboardRepository()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {
        return repository.loadCategory()
    }

    fun loadBanners(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }
}