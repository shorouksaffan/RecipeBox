package com.example.recipebox.ui.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.ui.components.MetaTag
import com.example.recipebox.ui.components.StepItem

@Composable
fun RecipeDetailScreen(
    recipeId: Long,
    viewModel: RecipeViewModel = hiltViewModel()
) {
    val recipe by viewModel.recipeState.collectAsState()

    LaunchedEffect(recipeId) {
        viewModel.getRecipeById(recipeId)
    }

    when(recipe) {
        is RecipeState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("An error occurred: ${(recipe as RecipeState.Error).error}")
            }
        }
        RecipeState.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        is RecipeState.Success -> {
            val recipe = (recipe as RecipeState.Success).recipe
            RecipeDetailContent(recipe = recipe)}

    }
}

@Composable
fun RecipeDetailContent(recipe: Recipe) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Introduction", "Ingredients")

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Recipe image
        item {
            AsyncImage(
                model = recipe.imageUri,
                contentDescription = recipe.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(6.dp))
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Title & meta
        item {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = recipe.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .fillMaxWidth()
                        .background(Color(0xFF3E5BA9))
                        .padding(8.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Tags from recipe
                    recipe.tags.forEach { tag ->
                        MetaTag(tag)
                    }

                    // Time based on steps
                    MetaTag("${recipe.steps.size * 5} Min")
                }
            }
        }

        // Tabs
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                tabs.forEachIndexed { index, title ->
                    Text(
                        text = title,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.clickable { selectedTab = index }
                    )
                }
            }
        }

        // Tab content
        if (selectedTab == 0) {
            // Steps
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("${recipe.steps.size} Steps", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(Modifier.height(8.dp))
                }
            }
            items(recipe.steps.size) { i ->
                StepItem(stepNumber = i + 1, text = recipe.steps[i])
            }
        } else {
            // Ingredients
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("${recipe.ingredients.size} Ingredients", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Spacer(Modifier.height(8.dp))
                }
            }
            items(recipe.ingredients.size) { i ->
                StepItem(stepNumber = i + 1, text = recipe.ingredients[i])
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewRecipeDetailContent() {
    val fakeRecipe = Recipe(
        title = "Perfect homemade pancake",
        imageUri = null, // preview won’t load remote images
        ingredients = listOf(
            "2 cups all-purpose flour",
            "2 teaspoons baking powder",
            "2 tablespoons sugar",
            "1/2 teaspoon salt",
            "1.5 cups milk",
            "1 large egg",
            "2 tablespoons vegetable oil or melted butter",
            "Vanilla extract (optional)"
        ),
        steps = listOf(
            "Mix flour, baking powder, sugar, and salt.",
            "Whisk milk, egg, and oil/butter.",
            "Add wet mix to dry mix, stir until smooth.",
            "Heat skillet, pour batter.",
            "Flip pancake when bubbles form.",
            "Repeat with remaining batter.",
            "Serve warm with toppings.",
            "Enjoy your pancakes!"
        ),
        tags = listOf("Low Calory", "Simple", "48 Min")
    )

    RecipeDetailContent(recipe = fakeRecipe)
}
