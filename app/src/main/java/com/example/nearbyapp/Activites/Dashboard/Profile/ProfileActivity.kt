package com.example.nearbyapp.Activites.Profile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.nearbyapp.Activites.Auth.LoginActivity
import com.example.nearbyapp.R
import com.example.nearbyapp.ViewModel.AuthViewModel

class ProfileActivity : ComponentActivity() {
    private val viewModel = AuthViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileScreen(viewModel)
        }
    }

    @Composable
    fun ProfileScreen(viewModel: AuthViewModel) {
        val context = LocalContext.current
        val userProfile by viewModel.getUserProfile().observeAsState()

        var phone by remember { mutableStateOf("") }
        var address by remember { mutableStateOf("") }
        var isEditing by remember { mutableStateOf(false) }

        LaunchedEffect(userProfile) {
            userProfile?.let {
                phone = it.phone
                address = it.address
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.lightBlue))
                .verticalScroll(rememberScrollState())
        ) {
            // Header con foto y nombre
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(colorResource(R.color.blue))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Foto de perfil
                    Image(
                        painter = rememberAsyncImagePainter(userProfile?.photoUrl),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentScale = ContentScale.Crop
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nombre
                    Text(
                        text = userProfile?.name ?: "Usuario",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    // Email
                    Text(
                        text = userProfile?.email ?: "",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // Tarjeta de información
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Información Personal",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.darkBrown)
                        )
                        TextButton(onClick = { isEditing = !isEditing }) {
                            Text(
                                text = if (isEditing) "Cancelar" else "Editar",
                                color = colorResource(R.color.blue)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo de teléfono
                    Text(
                        text = "Teléfono",
                        fontSize = 12.sp,
                        color = colorResource(R.color.darkBrown).copy(alpha = 0.6f)
                    )
                    if (isEditing) {
                        OutlinedTextField(
                            value = phone,
                            onValueChange = { phone = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            placeholder = { Text("+51 999 999 999") },
                            singleLine = true
                        )
                    } else {
                        Text(
                            text = phone.ifEmpty { "No registrado" },
                            fontSize = 16.sp,
                            color = colorResource(R.color.darkBrown),
                            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo de dirección
                    Text(
                        text = "Dirección",
                        fontSize = 12.sp,
                        color = colorResource(R.color.darkBrown).copy(alpha = 0.6f)
                    )
                    if (isEditing) {
                        OutlinedTextField(
                            value = address,
                            onValueChange = { address = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            placeholder = { Text("Av. España 123, Trujillo") },
                            singleLine = true
                        )
                    } else {
                        Text(
                            text = address.ifEmpty { "No registrada" },
                            fontSize = 16.sp,
                            color = colorResource(R.color.darkBrown),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    // Botón guardar (solo visible cuando está editando)
                    if (isEditing) {
                        Spacer(modifier = Modifier.height(24.dp))
                        Button(
                            onClick = {
                                viewModel.updateUserProfile(phone, address) { success ->
                                    if (success) {
                                        isEditing = false
                                        Toast.makeText(
                                            context,
                                            "Perfil actualizado",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Error al actualizar",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(R.color.blue)
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Guardar Cambios", color = Color.White)
                        }
                    }
                }
            }

            // Botón cerrar sesión
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Button(
                    onClick = {
                        viewModel.signOut()
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                        finish()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red.copy(alpha = 0.1f)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Cerrar Sesión", color = Color.Red, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
