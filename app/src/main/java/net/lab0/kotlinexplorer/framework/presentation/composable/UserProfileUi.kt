package net.lab0.kotlinexplorer.framework.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.DeferredResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun UserProfileUi(
    email: String?,
    profilePicturePlaceholder: DeferredResource<ImageVector>? = null,
    profilePicture: ImageBitmap? = null,
    logIn: () -> Unit,
    logOut: () -> Unit,
) {
  Column(
      modifier = Modifier.fillMaxWidth(),
  ) {

    Box(modifier = Modifier.height(192.dp)) {
      Surface(
          modifier = Modifier
              .height(128.dp)
              .fillMaxWidth(),
          color = MaterialTheme.colors.primary,
      ) {}

      Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
      ) {
        Surface(
            modifier = Modifier
                .padding(top = 64.dp)
                .size(128.dp),
            color = MaterialTheme.colors.surface,
        ) {
          val padding = Modifier.padding(8.dp)
          val placeholderVectorImage: ImageVector? = profilePicturePlaceholder?.resource?.resource
          when {
            profilePicture != null ->
              Image(profilePicture, modifier = padding)

            placeholderVectorImage != null ->
              Image(placeholderVectorImage, modifier = padding)

            else ->
              Surface(modifier = padding, color = Color.Gray) {}
          }
        }
      }
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {
      if (email != null) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = email,
            style = MaterialTheme.typography.h6
        )
        // TODO: flat button
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = logOut,
        ) {
          Text(
              text = "Log out".toUpperCase(Locale.getDefault()),
              style = MaterialTheme.typography.h6,
          )
        }
      } else {
        // TODO: flat button
        Button(
            onClick = logIn,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        ) {
          Text(
              text = "Log in",
              style = MaterialTheme.typography.h6,
          )
        }
      }
    }
  }
}

@Preview
@Composable
fun UserProfileUiPreview() {
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
          UserProfileUi(
              email = "foo@example.com",
              profilePicturePlaceholder = null,
              profilePicture = null,
              logIn = {},
              logOut = {},
          )
        }
      }
    }
  }
}
