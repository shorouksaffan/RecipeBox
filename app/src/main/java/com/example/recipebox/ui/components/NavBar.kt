package com.example.recipebox.ui.components

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipebox.R
import com.example.recipebox.core.utils.navigation.Navigation

@Composable
fun NavBar(
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    val navController = NavHostController(LocalContext.current)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
            onClick = {
                onItemSelected("search")
                if (currentRoute != Navigation.SearchScreen.route) {
                    navController.navigate(Navigation.SearchScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                      },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFFF6339),
                selectedTextColor = Color(0xFFFF6339),
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(
                painter = painterResource(id = R.drawable.add_circle),
                contentDescription = "Filter Icon",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            ) },
            label = { Text("Add New\n Recipe", fontSize = 8.sp) },
            selected = selectedItem == "add",
            onClick = {
                onItemSelected("add")
                if (currentRoute != Navigation.AddRecipeScreen.route) {
                    navController.navigate(Navigation.AddRecipeScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                      },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFFF6339),
                selectedTextColor = Color(0xFFFF6339),
                unselectedIconColor = Color.White,
                unselectedTextColor = Color.White
            )
        )
        NavigationBarItem(
            icon = { Icon(
                painter = painterResource(id = R.drawable.archive),
                contentDescription = "Save Icon",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            ) },
            label = { Text("Save") },
            selected = selectedItem == "save",
            onClick = {
                onItemSelected("save")
                if (currentRoute != Navigation.SavedScreen.route) {
                    navController.navigate(Navigation.SavedScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                      },
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
