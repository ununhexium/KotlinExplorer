package com.example.jetpackcomposeexplorer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.example.jetpackcomposeexplorer.db.ExplorerDatabase
import com.example.jetpackcomposeexplorer.model.ExploreViewModel
import com.example.jetpackcomposeexplorer.ui.frame.Body
import com.example.jetpackcomposeexplorer.ui.theme.JetpackComposeExplorerTheme

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val t = Thread {
      ExplorerDatabase.init(applicationContext)
    }
    t.start()
    t.join()

    setContent {
      JetpackComposeExplorerTheme {
        Body()
      }
    }
  }
}
