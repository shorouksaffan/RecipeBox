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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.recipebox.R
import com.example.recipebox.ui.components.CollectionCard
import com.example.recipebox.ui.components.NavBar

@Composable
fun SavedScreen(
    onBackClick: () -> Unit,
    onAddClick: () -> Unit,
    onPreviewClick: (CollectionItem) -> Unit,
    viewModel: SavedViewModel = hiltViewModel()
) {
    val collections by viewModel.collections.collectAsState()
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
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            modifier = Modifier.padding(innerPadding),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(collections) { item ->
                CollectionCard(
                    title = item.title,
                    imageUri = item.imageUrl,
                    onPreviewClick = { onPreviewClick(item) }
                )
            }
        }
    }
}


