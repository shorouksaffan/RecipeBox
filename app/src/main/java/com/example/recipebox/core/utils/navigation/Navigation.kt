package com.example.recipebox.core.utils.navigation

enum class Screen {
    SPLASH,
    ONBOARDING,
    ADD_RECIPE,
    INGREDIENTS,
    STEPS,
    COLLECTIONS,
    COLLECTION_DETAIL,
    SAVED,
    SEARCH,
    RECIPE_DETAIL

}

sealed class Navigation(val route: String) {
    object SplashScreen : Navigation(Screen.SPLASH.name)
    object OnboardingScreen : Navigation(Screen.ONBOARDING.name)
    object AddRecipeScreen : Navigation(Screen.ADD_RECIPE.name)
    object IngredientsScreen : Navigation(Screen.INGREDIENTS.name)
    object StepsScreen : Navigation(Screen.STEPS.name)
    object CollectionsScreen : Navigation(Screen.COLLECTIONS.name)
    object CollectionDetailScreen : Navigation(Screen.COLLECTION_DETAIL.name)
    object SavedScreen : Navigation(Screen.SAVED.name)
    object SearchScreen : Navigation(Screen.SEARCH.name)
    object RecipeDetailScreen : Navigation(Screen.RECIPE_DETAIL.name)

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}