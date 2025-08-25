package com.example.recipebox.ui.addrecipe.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.recipebox.ui.theme.RecipeBoxTheme

@Composable
fun AddIngredientDialog(
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

@Preview
@Composable
fun AddIngredientDialogPreview() {
    RecipeBoxTheme {
        val isShowingDialog = remember { mutableStateOf(false) }
        TextButton(onClick = { isShowingDialog.value = true }) { Text("Show Dialog") }
        if (isShowingDialog.value) {
            AddIngredientDialog(
                onDismiss = { isShowingDialog.value = false },
                onConfirm = { isShowingDialog.value = false }
            )
        }
    }
}