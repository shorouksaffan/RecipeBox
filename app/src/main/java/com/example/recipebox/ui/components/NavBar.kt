package com.example.recipebox.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NavBar(
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = Color(0xFF3E5BA9), // blue background
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = selectedItem == "home",
            onClick = { onItemSelected("home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFFF6339),   // <-- custom selected color
                selectedTextColor = Color(0xFFFF6339),
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = selectedItem == "search",
            onClick = { onItemSelected("search") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFFF6339),
                selectedTextColor = Color(0xFFFF6339),
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Add, contentDescription = "Add New") },
            label = { Text("Add New") },
            selected = selectedItem == "add",
            onClick = { onItemSelected("add") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFFF6339),
                selectedTextColor = Color(0xFFFF6339),
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AddCircle, contentDescription = "Save") },
            label = { Text("Save") },
            selected = selectedItem == "save",
            onClick = { onItemSelected("save") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFFF6339),
                selectedTextColor = Color(0xFFFF6339),
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = selectedItem == "profile",
            onClick = { onItemSelected("profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFFF6339),
                selectedTextColor = Color(0xFFFF6339),
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
    }
}

@Preview
@Composable
fun test() {
    NavBar(
        selectedItem = "search",
        onItemSelected = {}
    )
}
