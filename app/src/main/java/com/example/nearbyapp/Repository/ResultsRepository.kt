package com.example.nearbyapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nearbyapp.Domain.CategoryModel
import com.example.nearbyapp.Domain.StoreModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ResultsRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun LoadSubCategory(id: String): LiveData<MutableList<CategoryModel>> {
        val listData = MutableLiveData<MutableList<CategoryModel>>()
        val ref = firebaseDatabase.getReference("SubCategory")
        val query: Query = ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<CategoryModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(CategoryModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // ✅ CORREGIDO: Manejo de errores implementado
                Log.e("ResultsRepository", "Error al cargar subcategorías: ${error.message}")
                listData.value = mutableListOf()
            }
        })
        return listData
    }

    fun LoadPopular(id: String): LiveData<MutableList<StoreModel>> {
        val listData = MutableLiveData<MutableList<StoreModel>>()
        val ref = firebaseDatabase.getReference("Stores")
        val query: Query = ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<StoreModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(StoreModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // ✅ CORREGIDO: Manejo de errores implementado
                Log.e("ResultsRepository", "Error al cargar tiendas populares: ${error.message}")
                listData.value = mutableListOf()
            }
        })
        return listData
    }

    fun LoadNearest(id: String): LiveData<MutableList<StoreModel>> {
        val listData = MutableLiveData<MutableList<StoreModel>>()
        val ref = firebaseDatabase.getReference("Nearest")
        val query: Query = ref.orderByChild("CategoryId").equalTo(id)

        query.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<StoreModel>()
                for (childSnapshot in snapshot.children) {
                    val list = childSnapshot.getValue(StoreModel::class.java)
                    if (list != null) {
                        lists.add(list)
                    }
                }
                listData.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
                // ✅ CORREGIDO: Manejo de errores implementado
                Log.e("ResultsRepository", "Error al cargar tiendas cercanas: ${error.message}")
                listData.value = mutableListOf()
            }
        })
        return listData
    }
}