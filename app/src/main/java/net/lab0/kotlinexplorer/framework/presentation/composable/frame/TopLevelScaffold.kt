package net.lab0.kotlinexplorer.framework.presentation.composable.frame

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch

@Composable
fun TopLevelScaffold(
  navController: NavHostController,
  title: String,
  scaffoldState: ScaffoldState,
  quickScreens: List<TopLevelScreen>,
  content: @Composable (PaddingValues) -> Unit,
) {
  val coroutineScope = rememberCoroutineScope()

  Scaffold(
    scaffoldState = scaffoldState,
    drawerContent = {
      ExploreDrawer(
        onProfile = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
            navController.navigate(TopLevelScreen.Profile.routeDefinition)
          }
        },
        onLessonsSelected = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
            navController.navigate(TopLevelScreen.Chapters.routeDefinition)
          }
        },
        onToolsSelected = {
          coroutineScope.launch {
            scaffoldState.drawerState.close()
            navController.navigate(TopLevelScreen.Tools.routeDefinition)
          }
        },
      )
    },
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.onSurface,
          )
        },
        navigationIcon = {
          IconButton(
            onClick = {
              coroutineScope.launch {
                scaffoldState.drawerState.open()
              }
            },
          ) {
            Icon(
              Icons.Default.Menu,
              contentDescription = "Menu",
              tint = MaterialTheme.colors.onSurface,
            )
          }
        },
        elevation = 4.dp,
      )
    },
    bottomBar = {
      BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
        quickScreens.forEach { screen ->
          BottomNavigationItem(
            icon = {
              Icon(
                screen.icon,
                contentDescription = "",
                tint = MaterialTheme.colors.primary
              )
            },
            label = {
              Text(screen.name, color = MaterialTheme.colors.primary)
            },
            selected = currentRoute == screen.routeDefinition,
            onClick = {
              navController.navigate(screen.routeDefinition) {
                popUpTo = navController.graph.startDestination
                launchSingleTop = true
              }
            }
          )
        }
      }
    },
    content = content,
  )
}


@Preview
@Composable
fun TopLevelScaffoldPreview() {
  val state = rememberScaffoldState()

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
          TopLevelScaffold(
            rememberNavController(),
            "The Section",
            state,
            listOf(),
          ) {
            Text("Body")
          }
        }
      }
    }
  }
}
