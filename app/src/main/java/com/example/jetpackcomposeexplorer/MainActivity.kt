package com.example.jetpackcomposeexplorer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.example.jetpackcomposeexplorer.ui.JetpackComposeExplorerTheme
import com.example.jetpackcomposeexplorer.ui.frame.Body

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      JetpackComposeExplorerTheme {
        Body()
      }
    }
  }
}
