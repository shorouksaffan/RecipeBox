package com.example.recipebox.ui.addrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipebox.R
import com.example.recipebox.ui.components.progressInd_step2
import com.example.recipebox.ui.components.progressInd_step3


private val LightBlueTopAppBarColor = Color(0xFF4058A0)
private val OrangeHeaderColor = Color(0xFFFF9800)
private val YellowHighlightColor = Color(0xFFe9c46a)
private val TextColor = Color(0xFFFFFFFF)
private val blackColor = Color(0xFF000000)


private val TopBarBlue = Color(0xFF3E4FB1)
private val ScreenBg = Color(0xFFF4F8EE)
private val StepTrack = Color(0xFF1A1A1A)
private val StepPill = Color(0xFFFFD563)
private val BadgeOrange = Color(0xFFFE7C3F)
private val CardWhite = Color(0xFFFFFFFF)
private val PlusGray = Color(0xFFE1E1E1)
private val BottomBlack = Color(0xFF111111)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStepsScreen(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onNext: (List<String>) -> Unit = {}
) {
    val ingredients = remember { mutableStateListOf<String>() }
    var showAddDialog by remember { mutableStateOf(false) }

    if (showAddDialog) {
        AddstepDialog(
            onDismiss = { showAddDialog = false },
            onConfirm = { text ->
                if (text.isNotBlank()) ingredients += text.trim()
                showAddDialog = false
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "New Recipe",
                        color = TextColor,
                        fontWeight = FontWeight.Bold,
                        fontFamily = AQRADA,
                        fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back button */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = TextColor
                        )
                    }
                },
                actions = {
                    Button(
                        onClick = { /* Handle clear all */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = TextColor
                        ),
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text("Clear all", fontSize = 14.sp)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LightBlueTopAppBarColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            )
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { onNext(ingredients.toList()) },
                    enabled = ingredients.isNotEmpty(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(28.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BottomBlack,
                        disabledContainerColor = BottomBlack.copy(alpha = 0.3f)
                    ),
                    contentPadding = PaddingValues(vertical = 0.dp)
                ) {
                    Text("Next", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(ScreenBg)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {

            progressInd_step3()

            Spacer(Modifier.height(16.dp))


            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(ingredients, key = { _, item -> item }) { index, item ->
                    stepsRow(number = index + 1, text = item)
                }

                item {

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(PlusGray)
                            .clickable { showAddDialog = true },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add ingredient")
                    }
                }

                // Extra bottom spacing so the last item isn't hidden by the bottom bar
                item { Spacer(Modifier.height(64.dp)) }
            }
        }
    }
}



@Composable
private fun StepCircle(number: String) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .background(StepTrack),
        contentAlignment = Alignment.Center
    ) {
        Text(number, color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}



@Composable
private fun stepsRow(number: Int, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Number badge
        Box(
            modifier = Modifier
                .width(44.dp)
                .height(30.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(BadgeOrange),
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
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .background(CardWhite)
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

@Composable
private fun AddstepDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = { onConfirm(value) },
                enabled = value.isNotBlank()
            ) { Text("Add") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel") } },
        title = { Text("Add ingredient") },
        text = {
            OutlinedTextField(
                value = value,
                onValueChange = { value = it },
                singleLine = false,
                minLines = 1,
                maxLines = 3,
                placeholder = { Text("e.g., 2 cups all-purpose flour") }
            )
        }
    )
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun AddIngredientsScreen_Preview() {
    MaterialTheme {
        AddStepsScreen()
    }
}