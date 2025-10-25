package com.example.nearbyapp.Activites.Dashboard.Map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearbyapp.Activites.Dashboard.Results.ItemsNearest
import com.example.nearbyapp.Domain.StoreModel
import com.example.nearbyapp.R
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MapActivity : AppCompatActivity() {
    private lateinit var item: StoreModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            item = intent.getSerializableExtra("object") as? StoreModel ?: run {
                Toast.makeText(this, "Error al cargar los datos", Toast.LENGTH_SHORT).show()
                finish()
                return@setContent
            }

            val latitude = item.Latitude
            val longitude = item.Longitude

            val finalLatLng = if (latitude != 0.0 && longitude != 0.0) {
                LatLng(latitude, longitude)
            } else {
                LatLng(-8.1116, -79.0288)
            }

            MapScreen(finalLatLng, item)
        }
    }

    @Composable
    fun MapScreen(latLng: LatLng, item: StoreModel) {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(latLng, 15f)
        }
        val context = LocalContext.current

        // ✅ SOLUCIÓN: Configuración correcta de propiedades del mapa
        val uiSettings = MapUiSettings(
            zoomControlsEnabled = true,
            zoomGesturesEnabled = true,
            scrollGesturesEnabled = true,
            tiltGesturesEnabled = true,
            rotationGesturesEnabled = true,
            myLocationButtonEnabled = false
        )

        val properties = MapProperties(
            mapType = MapType.NORMAL,
            isMyLocationEnabled = false
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            // ✅ Mapa con propiedades correctas
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = properties,
                uiSettings = uiSettings
            ) {
                Marker(
                    state = MarkerState(position = latLng),
                    title = item.Title,
                    snippet = item.Address
                )
            }

            // Card inferior con información
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                    )
                    .padding(16.dp)
                    .heightIn(max = 300.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                ItemsNearest(item)

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.blue)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        if (item.Call.isNotEmpty() && item.Call != "+123456789") {
                            val phoneNumber = "tel:${item.Call}"
                            val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber))
                            context.startActivity(dialIntent)
                        } else {
                            Toast.makeText(
                                context,
                                "Número de teléfono no disponible",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text(
                        "Llamar a la tienda",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}