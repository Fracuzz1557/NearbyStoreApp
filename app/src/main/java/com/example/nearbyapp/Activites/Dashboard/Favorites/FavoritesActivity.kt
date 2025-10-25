package com.example.nearbyapp.Activites.Favorites

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearbyapp.Activites.Dashboard.Results.ItemsNearest
import com.example.nearbyapp.Domain.StoreModel
import com.example.nearbyapp.R
import com.example.nearbyapp.ViewModel.AuthViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FavoritesActivity : ComponentActivity() {
    private val authViewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FavoritesScreen()
        }
    }

    @Composable
    fun FavoritesScreen() {
        var favoriteStores by remember { mutableStateOf<List<StoreModel>>(emptyList()) }
        var isLoading by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            loadFavorites { stores ->
                favoriteStores = stores
                isLoading = false
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.lightBlue))
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(colorResource(R.color.blue)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Mis Favoritos",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }

            // Contenido
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = colorResource(R.color.blue))
                }
            } else if (favoriteStores.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "No tienes favoritos aún",
                            fontSize = 18.sp,
                            color = colorResource(R.color.darkBrown),
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Agrega tiendas a tu lista de deseos",
                            fontSize = 14.sp,
                            color = colorResource(R.color.darkBrown).copy(alpha = 0.6f),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(favoriteStores) { store ->
                        ItemsNearest(item = store)
                    }
                }
            }
        }
    }

    private fun loadFavorites(onComplete: (List<StoreModel>) -> Unit) {
        val userId = authViewModel.getCurrentUserId() ?: return onComplete(emptyList())
        val database = FirebaseDatabase.getInstance()
        val favoritesRef = database.getReference("Favorites").child(userId)

        favoritesRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val stores = mutableListOf<StoreModel>()
                for (child in snapshot.children) {
                    val store = child.getValue(StoreModel::class.java)
                    store?.let { stores.add(it) }
                }
                onComplete(stores)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FavoritesActivity", "Error: ${error.message}")
                onComplete(emptyList())
            }
        })
    }
}

