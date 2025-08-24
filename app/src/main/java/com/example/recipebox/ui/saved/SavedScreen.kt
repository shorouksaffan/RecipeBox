@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.recipebox.ui.saved

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebox.R
import com.example.recipebox.domain.model.Collection
import com.example.recipebox.ui.components.CollectionCard
import com.example.recipebox.ui.components.NavBar

@Composable
fun SavedScreen(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    onPreviewClick: (Collection) -> Unit,
    viewModel: SavedViewModel = hiltViewModel()
) {
    val collections by viewModel.savedState.collectAsState()
    var selectedItem = "save" // ✅ default for this screen

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Saved",
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
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
                    IconButton(onClick = onAddClick) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_circle),
                            contentDescription = "Add",
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF3F51B5) // blue background
                )
            )
        },
        bottomBar = {
            NavBar(
                selectedItem = selectedItem,
                onItemSelected = { item -> selectedItem = item }
            )
        }
    ) { innerPadding ->
        when (collections) {
            is SavedState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is SavedState.Error -> {
                val message = (collections as SavedState.Error).error
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Failed to load collections", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = message, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.getCollections() }) {
                        Text("Retry")
                    }
                }
            }

            is SavedState.Success -> {
                val collections = (collections as SavedState.Success).collections
                if (collections.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "No saved collections yet.", style = MaterialTheme.typography.bodyLarge)
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(12.dp),
                        modifier = Modifier.padding(innerPadding),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(collections) { item ->
                            CollectionCard(
                                title = item.name,
                                imageUri = item.imageUrl ?: "",
                                onPreviewClick = { onPreviewClick(item) }
                            )
                        }
                    }
                }
            }
        }
    }
}


