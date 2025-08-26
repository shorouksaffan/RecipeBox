package com.example.recipebox.ui.addrecipe.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebox.ui.theme.RecipeBoxTheme
import com.example.recipebox.ui.theme.White

@Composable
fun IngredientRow(number: Int, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .width(44.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "%02d".format(number),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.width(10.dp))


        Box(
            modifier = Modifier
                .weight(1f)
                .heightIn(min = 52.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                )
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                .background(White)
                .padding(horizontal = 14.dp, vertical = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color(0xFF1F1F1F),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
private fun IngredientRowPreview() {
    RecipeBoxTheme {
        IngredientRow(1, "2 cups of flour, sifted")
    }
}

@Preview
@Composable
private fun IngredientRowPreviewLongText() {
    RecipeBoxTheme {
        IngredientRow(12, "1 tablespoon of extra virgin olive oil, preferably cold-pressed from a reputable source")
    }
}