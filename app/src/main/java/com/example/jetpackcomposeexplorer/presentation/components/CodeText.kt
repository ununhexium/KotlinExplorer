package com.example.jetpackcomposeexplorer.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CodeText(code:String) {
    Text(code, fontFamily = FontFamily.Monospace)
}

@Preview
@Composable
fun CodeTextPreview() {
    MaterialTheme {
        Surface {
            Column {
                CodeText("Wii")
            }
        }
    }
}
