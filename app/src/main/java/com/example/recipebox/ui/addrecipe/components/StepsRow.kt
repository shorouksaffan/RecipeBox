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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebox.ui.theme.White

@Composable
fun StepsRow(number: Int, text: String) {
    Row(
        modifier = Modifier.Companion.fillMaxWidth(),
        verticalAlignment = Alignment.Companion.CenterVertically
    ) {
        // Number badge
        Box(
            modifier = Modifier.Companion
                .width(44.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(MaterialTheme.colorScheme.secondary),
            contentAlignment = Alignment.Companion.Center
        ) {
            Text(
                text = "%02d".format(number),
                color = Color.Companion.White,
                fontWeight = FontWeight.Companion.Bold,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.Companion.width(10.dp))


        Box(
            modifier = Modifier.Companion
                .weight(1f)
                .heightIn(min = 52.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
                )
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                .background(White)
                .padding(horizontal = 14.dp, vertical = 12.dp),
            contentAlignment = Alignment.Companion.CenterStart
        ) {
            Text(
                text = text,
                fontSize = 14.sp,
                color = Color(0xFF1F1F1F),
                maxLines = 2,
                overflow = TextOverflow.Companion.Ellipsis
            )
        }
    }
}