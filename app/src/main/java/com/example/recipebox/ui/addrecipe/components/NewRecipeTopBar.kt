package com.example.recipebox.ui.addrecipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.recipebox.ui.theme.RecipeBoxTheme
import com.example.recipebox.ui.theme.RecipeBoxTypography
import com.example.recipebox.ui.theme.White

@Composable
fun NewRecipeTopBar(
    onClickBack: () -> Unit,
    onClickClearAll: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBackIos,
            contentDescription = "Back",
            tint = White,
            modifier = Modifier.clickable(onClick = onClickBack)
        )
        Text(
            text = "Add New Recipe",
            style = RecipeBoxTypography.h6,
            color = White,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        )
        Text(
            text = "Clear All",
            style = RecipeBoxTypography.body3,
            color = White,
            modifier = Modifier.clickable(onClick = onClickClearAll)
        )
    }
}

@Composable
@PreviewLightDark
fun NewRecipeTopBarPreview() {
    RecipeBoxTheme {
        NewRecipeTopBar(onClickBack = {}, onClickClearAll = {})
    }
}