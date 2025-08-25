package com.example.recipebox.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebox.R

// Define colors and typography based on the XML
val DarkGray = Color(0xFF353535)
val YellowishGreen = Color(0xFFDEE21B)
val LightGray = Color(0xFF717171)

// You'll need to add a custom font if you use Montserrat.
// For example: val Montserrat = FontFamily(Font(R.font.montserrat))
// Using default for this example.

val AQRADA = FontFamily(
    Font(R.font.aqrada, FontWeight.Normal)
)
@Composable
fun progressInd_step1() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 32.dp)
    ) {
        // "Rectangle 1" - The main container with rounded ends.
        Box(
            modifier = Modifier
                .width(141.dp)
                .height(32.dp)
                .offset(x = 32.dp)
                .background(color = DarkGray, shape = RoundedCornerShape(16.dp))
        )

        // "Ellipse 49" - Left circle
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(color = DarkGray, shape = CircleShape)
        )

        // "Ellipse 50" - Middle circle
        Box(
            modifier = Modifier
                .size(32.dp)
                .offset(x = 182.dp) // 214dp - 32dp (start offset)
                .background(color = DarkGray, shape = CircleShape)
        )

        // "Ellipse 51" - Right circle
        Box(
            modifier = Modifier
                .size(32.dp)
                .offset(x = 222.dp) // 254dp - 32dp (start offset)
                .background(color = DarkGray, shape = CircleShape)
        )

        // "Some_id" (1)
        Text(
            text = "1",
            color = YellowishGreen,
            fontSize = 12.sp,

            modifier = Modifier.offset(x = 13.dp, y = 9.dp) // 45dp - 32dp (start offset)
        )

        // "Recipe Information"
        Text(
            text = "Recipe Information",
            color = YellowishGreen,
            fontSize = 8.sp,
            fontFamily = AQRADA,
            modifier = Modifier.offset(x = 43.dp, y = 9.dp) // 75dp - 32dp (start offset)
        )

        // "Some_id" (2)
        Text(
            text = "2",
            color = LightGray,
            fontSize = 12.sp,
            modifier = Modifier.offset(x = 195.dp, y = 9.dp) // 227dp - 32dp (start offset)
        )

        // "Some_id" (3)
        Text(
            text = "3",
            color = LightGray,
            fontSize = 12.sp,
            modifier = Modifier.offset(x = 235.dp, y = 9.dp) // 267dp - 32dp (start offset)
        )

        // Note: The small rectangles (16.92dp x 14.25dp) are part of the original design but
        // are visually represented by the spacing between the main elements in the Compose layout.
        // If you need them to be visible, you would add them here with appropriate offsets.
    }
}


@Composable
fun progressInd_step2() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min).padding(top = 20.dp, start = 32.dp)
    ) {
        // Main Background Rectangle
        Canvas(
            modifier = Modifier
                .align(Alignment.Center)
                .width(141.dp)
                .height(32.dp)
        ) {
            drawRoundRect(
                color = Color(0xFF353535),
                cornerRadius = CornerRadius(16.dp.toPx()),
                size = size
            )
        }

        // Left-most circle with number 1
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 33.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(32.dp)) {
                drawCircle(color = Color(0xFF353535))
            }
            Text(
                text = "1",
                color = Color(0xFFDEE21B),
                fontSize = 12.sp,
                fontFamily = Montserrat,
                modifier = Modifier.offset(y = (-1).dp) // Adjust for vertical alignment
            )
        }

        // Second circle with number 2
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 73.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(32.dp)) {
                drawCircle(color = Color(0xFF353535))
            }
            Text(
                text = "2",
                color = Color(0xFFDEE21B),
                fontSize = 12.sp,
                fontFamily = Montserrat,
                modifier = Modifier.offset(y = (-1).dp) // Adjust for vertical alignment
            )
        }

        // Third circle with number 3
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = (-33).dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(32.dp)) {
                drawCircle(color = Color(0xFF353535))
            }
            Text(
                text = "3",
                color = Color(0xFF717171),
                fontSize = 12.sp,
                fontFamily = Montserrat,
                modifier = Modifier.offset(y = (-1).dp) // Adjust for vertical alignment
            )
        }

        // Ingredients Text
        Text(
            text = "Ingredients",
            color = Color(0xFFDEE21B),
            fontSize = 12.sp,
            fontFamily = Montserrat,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 10.dp, y = (-1).dp) // Adjusted to align with the rest
        )
    }
}

// Dummy font definition for the example
val Montserrat = FontFamily(Font(R.font.montserrat_regular))




@Composable
fun progressInd_step3() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min).padding(top = 20.dp, start = 32.dp)
    ) {
        // Background container for the circles and the "Steps" text
        Row(
            modifier = Modifier
                .width(286.dp)
                .height(32.dp)
                .align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // First circle
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF353535)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "1",
                    color = Color(0xFFDEE21B),
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    modifier = Modifier.offset(y = (-1).dp)
                )
            }

            // Second circle
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF353535)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    color = Color(0xFFDEE21B),
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    modifier = Modifier.offset(y = (-1).dp)
                )
            }

            // Third circle
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFF353535)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "3",
                    color = Color(0xFFDEE21B),
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    modifier = Modifier.offset(y = (-1).dp)
                )
            }

            // Background rectangle for "Steps" text
            Box(
                modifier = Modifier
                    .width(141.dp)
                    .height(32.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF353535)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Steps",
                    color = Color(0xFFDEE21B),
                    fontSize = 12.sp,
                    fontFamily = Montserrat,
                    modifier = Modifier.offset(y = (-1).dp)
                )
            }
        }
    }
}









@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RecipeInfoScreenPreview() {
    progressInd_step3()
}