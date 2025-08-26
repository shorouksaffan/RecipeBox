@file:OptIn(ExperimentalMaterial3Api::class)
package com.example.recipebox.ui.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebox.R
import com.example.recipebox.domain.model.Recipe
import com.example.recipebox.domain.model.Collection
import com.example.recipebox.domain.usecase.DeleteCollectionUseCase
import com.example.recipebox.ui.components.RecipeCard
import kotlinx.coroutines.launch

@Composable
fun CollectionDetailScreen(
    collection: Collection,
    savedCount: Int,
    recipes: List<Recipe>,
    deleteCollectionUseCase: DeleteCollectionUseCase,
    onBackClick: () -> Unit,
    onCollectionDeleted: () -> Unit,
    onUnsaveRecipe: (Recipe) -> Unit // 👈 callback for unsaving
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showEditSheet by remember { mutableStateOf(false) }
    var showUnsaveDialog by remember { mutableStateOf(false) } // 👈 state for unsave dialog
    var selectedRecipe by remember { mutableStateOf<Recipe?>(null) } // 👈 track recipe to unsave

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_left),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { showBottomSheet = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_lists_24),
                            contentDescription = "Menu",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3F51B5)
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF7FAF7))
                .padding(innerPadding)
        ) {
            // Title and saved count
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = collection.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$savedCount saved post",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            // Recipes Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(recipes) { recipe ->
                    RecipeCard(
                        recipe = recipe,
                        rating = 4.8,
                        onSaveClick = {
                            selectedRecipe = recipe
                            showUnsaveDialog = true
                        }
                    )
                }
            }
        }

        // --- Bottom Sheet Menu ---
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Delete collection",
                        color = Color.Red,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet = false
                                showDeleteDialog = true
                            }
                    )
                    Divider()
                    Text(
                        text = "Edit collection",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth()
                            .clickable {
                                showBottomSheet = false
                                showEditSheet = true
                            }
                    )
                    Divider()
                    Text(
                        text = "Select",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(vertical = 12.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

        // --- Delete Confirmation Dialog ---
        if (showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { showDeleteDialog = false },
                title = { Text("Delete collection?") },
                text = { Text("When you delete this collection, the photo will still be saved.") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDeleteDialog = false
                            scope.launch {
                                deleteCollectionUseCase(collection)
                                onCollectionDeleted()
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Delete", color = Color.White)
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showDeleteDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }

        // --- Unsave Confirmation Dialog ---
        if (showUnsaveDialog && selectedRecipe != null) {
            AlertDialog(
                onDismissRequest = { showUnsaveDialog = false },
                title = { Text("Remove from saved?") },
                text = { Text("Are you sure you want to remove this recipe from your saved list?") },
                confirmButton = {
                    Button(
                        onClick = {
                            selectedRecipe?.let { onUnsaveRecipe(it) }
                            showUnsaveDialog = false
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Remove", color = Color.White)
                    }
                },
                dismissButton = {
                    OutlinedButton(onClick = { showUnsaveDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
