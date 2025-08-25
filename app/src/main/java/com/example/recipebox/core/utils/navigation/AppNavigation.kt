package com.example.recipebox.core.utils.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.recipebox.ui.addrecipe.AddRecipeScreen
import com.example.recipebox.ui.addrecipe.IngredientsScreen
import com.example.recipebox.ui.addrecipe.StepsScreen
import com.example.recipebox.ui.onboarding.OnboardingScreen
import com.example.recipebox.ui.recipe.RecipeDetailScreen
import com.example.recipebox.ui.saved.CollectionsScreen
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
        navController = navController, startDestination = startDestination, modifier = modifier
    ) {
        composable(Navigation.IngredientsScreen.route) {
            IngredientsScreen()
        }
        composable(Navigation.StepsScreen.route) {
            StepsScreen()
        }
        composable(Navigation.CollectionDetailScreen.route) {
//            CollectionDetailScreen()
        }
        composable(Navigation.SavedScreen.route) {
//             SavedScreen()
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
            SearchScreen({
                navController.navigate(Navigation.RecipeDetailScreen.withArgs(it.id.toString()))
            })
        }
        composable(
            Navigation.RecipeDetailScreen.route + "/{recipeId}", arguments = listOf(
            navArgument("recipeId") { type = NavType.LongType })) { entry ->
            val recipeId = entry.arguments?.getLong("recipeId")
            RecipeDetailScreen(recipeId!!)
        }
        composable(Navigation.CollectionsScreen.route) {
            CollectionsScreen()
        }
    }
}