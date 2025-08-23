package com.example.recipebox.core.utils.navigation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipebox.R

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    ) {
    val items = listOf(
//        BottomNavigationItem(
//            title = "Home",
//            route = Navigation.HomeScreen.route,
//            icon = ImageVector.vectorResource(id = R.drawable.home)
//        ),
        BottomNavigationItem(
            title = "Search",
            route = Navigation.SearchScreen.route,
            icon = ImageVector.vectorResource(id = R.drawable.search_normal)
        ),
        BottomNavigationItem(
            title = "Add New",
            route = Navigation.AddRecipeScreen.route,
            icon = ImageVector.vectorResource(id = R.drawable.add_circle)
        ),
        BottomNavigationItem(
            title = "Save",
            route = Navigation.CollectionsScreen.route,
            icon = ImageVector.vectorResource(id = R.drawable.archive)
        ),
//        BottomNavigationItem(
//            title = "Profile",
//            route = Navigation.ProfileScreen.route,
//            icon = ImageVector.vectorResource(id = R.drawable.profile)
//        )
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = { Text(item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}