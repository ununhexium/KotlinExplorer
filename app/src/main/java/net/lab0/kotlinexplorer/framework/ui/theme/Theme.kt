package net.lab0.kotlinexplorer.framework.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import net.lab0.kotlinexplorer.framework.ui.theme.Theme.DARK_DEFAULT
import net.lab0.kotlinexplorer.framework.ui.theme.Theme.LIGHT_DEFAULT

private val DarkColorPalette = darkColors(
    primary = Orange800,
    primaryVariant = Orange800,
    secondary = Green500,
    background = Gray200,
    surface = Gray200,
    onBackground = BlueKotlinPantone
)

private val LightColorPalette = lightColors(
    primary = purpleKotlinPantone,
    primaryVariant = purple700,
    secondary = BlueKotlinPantone,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

enum class Theme {
  DARK_DEFAULT,
  LIGHT_DEFAULT
}

@Composable
fun JetpackComposeExplorerTheme(
    theme: Theme = if (isSystemInDarkTheme()) DARK_DEFAULT else LIGHT_DEFAULT,
    content: @Composable () -> Unit
) {
  val colors =
      when (theme) {
        DARK_DEFAULT -> darkColors()
        LIGHT_DEFAULT -> lightColors()
      }

  MaterialTheme(
      colors = colors,
      typography = typography,
      shapes = shapes,
      content = content
  )
}
