package net.lab0.kotlinexplorer.framework.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import net.lab0.kotlinexplorer.framework.ui.theme.Theme.KOTLIN_DARK
import net.lab0.kotlinexplorer.framework.ui.theme.Theme.KOTLIN_LIGHT

private val DarkColorPalette = darkColors(
    primary = PurpleKotlinPantone,
    primaryVariant = Purple700,
    secondary = BlueKotlinPantone,
//    secondaryVariant = Blue500,
)

private val LightColorPalette = lightColors(
    primary = PurpleKotlinPantone,
    primaryVariant = Purple700,
    secondary = BlueKotlinPantone,
    secondaryVariant = OrangeKotlinPantone,
)

enum class Theme {
  KOTLIN_DARK,
  KOTLIN_LIGHT
}

@Composable
fun JetpackComposeExplorerTheme(
    theme: Theme = if (isSystemInDarkTheme()) KOTLIN_DARK else KOTLIN_LIGHT,
    content: @Composable () -> Unit
) {
  val colors =
      when (theme) {
        KOTLIN_DARK -> DarkColorPalette
        KOTLIN_LIGHT -> LightColorPalette
      }

  MaterialTheme(
      colors = colors,
      typography = typography,
      shapes = shapes,
      content = content
  )
}
