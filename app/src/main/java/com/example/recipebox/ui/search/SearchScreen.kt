package com.example.recipebox.ui.search

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipebox.R
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.ui.components.NavBar
import com.example.recipebox.ui.components.RecipeCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun SearchScreen(
    recipes: List<Recipe>,
    onRecipeClick: (Recipe) -> Unit
) {
    var query by remember { mutableStateOf("") }
    var selectedTab by remember { mutableStateOf("search") }
    var showFilter by remember { mutableStateOf(false) } // toggle filter screen

    Scaffold(
        bottomBar = {
            NavBar(
                selectedItem = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Main Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                // Top Search Bar
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color(0xFF3E5BA9), shape = RoundedCornerShape(8.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = query,
                        onValueChange = { query = it },
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f)
                            .padding(start = 8.dp),
                        placeholder = {
                            Row {
                                Icon(Icons.Default.Search, contentDescription = "Search", Modifier.size(22.dp))
                                Text("Search...", color = Color.Black, fontSize = 18.sp)
                            }
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                    IconButton(
                        onClick = { showFilter = true }, // Open filter panel
                        modifier = Modifier
                            .padding(4.dp)
                            .background(Color.Yellow, shape = RoundedCornerShape(8.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_filter_alt_24),
                            contentDescription = "Filter Icon",
                            tint = Color.Black
                        )
                    }
                }

                // Section Header
                Text(
                    text = "Recipes",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 4.dp)
                )

                // Recipes Grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(recipes) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        )
                    }
                }
            }

            // Overlay + Sliding Filter
            if (showFilter) {
                Box(modifier = Modifier.fillMaxSize()) {

                    // Semi-transparent background overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.4f))
                            .clickable { showFilter = false } // close when tapping outside
                    )

                    // Sliding Filter Panel
                    AnimatedVisibility(
                        visible = showFilter,
                        enter = slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth }, // start off-screen RIGHT
                            animationSpec = tween(300)
                        ),
                        exit = slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth }, // slide back to RIGHT
                            animationSpec = tween(300)
                        ),
                        modifier = Modifier.align(Alignment.CenterEnd) // 🔑 place at right edge
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.8f) // 80% width
                                .background(Color.White)
                        ) {
                            FilterScreen()
                        }
                    }
                }
            }

        }
    }
}


@Preview
@Composable
fun test() {
    val sampleRecipes = List(6) {
        Recipe(
            id = it.toLong(),
            title = "chocolate cake with buttercream frosting",
            imageUri = "https://picsum.photos/300/300?random=$it",
            ingredients = emptyList(),
            steps = emptyList(),
            tags = emptyList()
        )
    }
    SearchScreen(
        sampleRecipes,
        {

        })
}