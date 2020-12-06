package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.BigVerticalSpacer

@Composable
fun TutorialCorrectNamePage(name: String, onClickHome: () -> Unit) {
  Column {
    Text("That's it $name")
    BigVerticalSpacer()
    Button(onClick = onClickHome) {
      Text("Home")
    }
  }
}

@Preview
@Composable
fun PreviewTutorialCorrectNamePage() {
  MaterialTheme {
    Surface {
      TutorialCorrectNamePage("Foo"){}
    }
  }
}
