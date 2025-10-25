package com.example.nearbyapp.Activites.Dashboard

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearbyapp.Activites.Favorites.FavoritesActivity
import com.example.nearbyapp.Activites.Profile.ProfileActivity
import com.example.nearbyapp.R

@Composable
@Preview
fun MyBottomBar() {
    val bottomMenuItemsList = prepareBottomMenu()
    val context = LocalContext.current
    var selected by remember { mutableStateOf("Inicio") }

    BottomAppBar(
        backgroundColor = colorResource(R.color.white),
        elevation = 3.dp
    ) {
        bottomMenuItemsList.forEach { bottomMenuItem ->
            BottomNavigationItem(
                selected = (selected == bottomMenuItem.label),
                onClick = {
                    selected = bottomMenuItem.label

                    when (bottomMenuItem.label) {
                        "Inicio" -> {
                            // Ya estamos en MainActivity
                        }
                        "Ayuda" -> {
                            android.widget.Toast.makeText(
                                context,
                                "Ayuda: Contacta a soporte@nearbystore.pe",
                                android.widget.Toast.LENGTH_LONG
                            ).show()
                        }
                        "Favoritos" -> {
                            context.startActivity(Intent(context, FavoritesActivity::class.java))
                        }
                        "Perfil" -> {
                            context.startActivity(Intent(context, ProfileActivity::class.java))
                        }
                    }
                },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Icon(
                            painter = bottomMenuItem.icon,
                            contentDescription = bottomMenuItem.label,
                            tint = if (selected == bottomMenuItem.label)
                                colorResource(R.color.blue)
                            else
                                colorResource(R.color.darkBrown),
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = bottomMenuItem.label,
                            fontSize = 11.sp,
                            color = if (selected == bottomMenuItem.label)
                                colorResource(R.color.blue)
                            else
                                colorResource(R.color.darkBrown),
                            modifier = Modifier.padding(top = 4.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            )
        }
    }
}

data class BottomMenuItem(
    val label: String,
    val icon: Painter
)

@Composable
fun prepareBottomMenu(): List<BottomMenuItem> {
    val bottomMenuItemList = arrayListOf<BottomMenuItem>()

    bottomMenuItemList.add(BottomMenuItem(label = "Inicio", icon = painterResource(R.drawable.btn_1)))
    bottomMenuItemList.add(BottomMenuItem(label = "Ayuda", icon = painterResource(R.drawable.btn_2)))
    bottomMenuItemList.add(BottomMenuItem(label = "Favoritos", icon = painterResource(R.drawable.btn_3)))
    bottomMenuItemList.add(BottomMenuItem(label = "Perfil", icon = painterResource(R.drawable.btn_4)))

    return bottomMenuItemList
}