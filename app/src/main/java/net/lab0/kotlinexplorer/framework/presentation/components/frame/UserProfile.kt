package net.lab0.kotlinexplorer.framework.presentation.components.frame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import net.lab0.kotlinexplorer.R

@Composable
fun UserProfile() {
  Column {
    Image(
        imageResource(id = R.drawable.ic_kotlin_logo),
        contentScale = ContentScale.Fit
    )
//    Row(
//        modifier = Modifier
//            .aspectRatio(1f)
//            .fillMaxWidth(),
//    ) {
//      Image(
//          imageResource(id = R.drawable.ic_kotlin_logo),
//          contentScale = ContentScale.Fit
//      )
//      Surface(contentColor = MaterialTheme.colors.primary) {
//
//      }
//    }
  }
}

@Preview
@Composable
fun UserProfilePreview() {
  MaterialTheme {
    Surface(
        color = Color(0xFF4CAF50)
    ) {
      Column(
          modifier = Modifier.padding(20.dp)
      ) {
        Surface(
            color = MaterialTheme.colors.surface
        ) {
          UserProfile()
        }
      }
    }
  }
}