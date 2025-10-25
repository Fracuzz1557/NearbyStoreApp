package com.example.nearbyapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.nearbyapp.Repository.AuthRepository
import com.example.nearbyapp.Repository.UserProfile

class AuthViewModel : ViewModel() {
    private val repository = AuthRepository()

    fun getUserProfile(): LiveData<UserProfile?> {
        return repository.getUserProfile()
    }

    fun updateUserProfile(phone: String, address: String, onComplete: (Boolean) -> Unit) {
        repository.updateUserProfile(phone, address, onComplete)
    }

    fun getCurrentUserId(): String? {
        return repository.getCurrentUserId()
    }

    fun getCurrentUserEmail(): String? {
        return repository.getCurrentUserEmail()
    }

    fun signOut() {
        repository.signOut()
    }
}