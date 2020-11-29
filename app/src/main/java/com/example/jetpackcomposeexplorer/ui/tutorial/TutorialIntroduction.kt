package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.BigVerticalSpacer

@Composable
fun TutorialIntroduction(onGo: () -> Unit) {
  Column {
    BigVerticalSpacer()
    Text("This quick guide will lead you through the basics of using this app")
    BigVerticalSpacer()
    Button(
        onClick = onGo,
        Modifier.align(Alignment.CenterHorizontally)
    ) {
      Text("Go!")
    }
  }
}

@Preview
@Composable
fun PreviewTutorialIntroduction() {
  MaterialTheme {
    Surface {
      TutorialIntroduction {}
    }
  }
}
