package com.example.jetpackcomposeexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import com.example.jetpackcomposeexplorer.ui.JetpackComposeExplorerTheme

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      JetpackComposeExplorerTheme {
        Surface(color = MaterialTheme.colors.background) {
          Scaffold(topBar = {
            TopAppBar {

            }
          }) {
            Text(text = "index")
          }
        }
      }
    }
  }
}

@Composable
fun Body() {
  JetpackComposeExplorerTheme {
    Surface(color = MaterialTheme.colors.background) {
      Scaffold(topBar = {
        TopAppBar(
            title = { Text("Jetpack Compose Explorer") },
            navigationIcon = { Icon(asset = Icons.Default.Menu) }
        )
      }) {
        Text(text = "index")
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  Body()
}