package com.example.recipebox.core.utils.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipebox.ui.addrecipe.AddRecipeScreen
import com.example.recipebox.ui.addrecipe.IngredientsScreen
import com.example.recipebox.ui.addrecipe.StepsScreen
import com.example.recipebox.ui.onboarding.OnboardingScreen
import com.example.recipebox.ui.saved.CollectionDetailScreen
import com.example.recipebox.ui.saved.CollectionsScreen
import com.example.recipebox.ui.saved.SavedScreen
import com.example.recipebox.ui.search.SearchScreen
import com.example.recipebox.ui.splash.SplashScreen


@Composable
@SuppressLint("ModifierParameter")
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = Navigation.AddRecipeScreen.route,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Navigation.IngredientsScreen.route) {
            IngredientsScreen()
        }
        composable(Navigation.StepsScreen.route) {
            StepsScreen()
        }
        composable(Navigation.CollectionDetailScreen.route) {
            CollectionDetailScreen()
        }
        composable(Navigation.SavedScreen.route) {
             SavedScreen()
        }
        composable(Navigation.SplashScreen.route) {
            SplashScreen({

            })
        }
        composable(Navigation.OnboardingScreen.route) {
            OnboardingScreen()
        }
        composable(Navigation.AddRecipeScreen.route) {
            AddRecipeScreen()
        }
        composable(Navigation.SearchScreen.route) {
//            SearchScreen()
        }
        composable(Navigation.CollectionsScreen.route) {
            CollectionsScreen()
        }
    }
}