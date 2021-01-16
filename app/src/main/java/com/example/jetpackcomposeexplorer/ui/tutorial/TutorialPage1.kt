package com.example.jetpackcomposeexplorer.ui.tutorial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.frame.BigVerticalSpacer
import com.example.jetpackcomposeexplorer.ui.frame.SmallHorizontalSpacer

@Composable
fun TutorialPage1(
    name: String?,
    setName: (String) -> Unit,
    onNameSubmit: (String) -> Unit
) {
  Box(Modifier.fillMaxWidth().padding(8.dp)) {
    Column(Modifier.fillMaxWidth().align(Alignment.Center)) {
      Text("Hello, what's your name?")
      BigVerticalSpacer()
      Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
        TextField(value = name ?: "", onValueChange = setName)
        SmallHorizontalSpacer()
        Button(
            onClick = { onNameSubmit(name ?: "") },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
          Text("Ok")
        }
      }
    }
  }
}

@Preview
@Composable
fun PreviewTutorialPage() {
  MaterialTheme {
    Surface {
      TutorialPage1("Foo", {}, {})
    }
  }
}
