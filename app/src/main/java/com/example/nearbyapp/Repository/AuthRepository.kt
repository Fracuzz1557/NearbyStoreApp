package com.example.nearbyapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

data class UserProfile(
    val name: String = "",
    val email: String = "",
    val photoUrl: String = "",
    val phone: String = "",
    val address: String = ""
)

class AuthRepository {
    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()

    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    fun getCurrentUserEmail(): String? {
        return auth.currentUser?.email
    }

    fun getUserProfile(): LiveData<UserProfile?> {
        val userData = MutableLiveData<UserProfile?>()
        val userId = getCurrentUserId()

        if (userId != null) {
            val userRef = database.getReference("Users").child(userId)
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val profile = snapshot.getValue(UserProfile::class.java)
                    userData.value = profile
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("AuthRepository", "Error al cargar perfil: ${error.message}")
                    userData.value = null
                }
            })
        } else {
            userData.value = null
        }

        return userData
    }

    fun updateUserProfile(phone: String, address: String, onComplete: (Boolean) -> Unit) {
        val userId = getCurrentUserId() ?: return onComplete(false)
        val userRef = database.getReference("Users").child(userId)

        val updates = mapOf(
            "phone" to phone,
            "address" to address
        )

        userRef.updateChildren(updates)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    fun signOut() {
        auth.signOut()
    }
}
