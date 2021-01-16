package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ExploreTopAppBar(state: ScaffoldState) {
    TopAppBar(
        navigationIcon = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clickable(
                        onClick = {
                            if (state.drawerState.isOpen) {
                                state.drawerState.close()
                            } else {
                                state.drawerState.open()
                            }
                        }
                    )
            ) {
                Text(text = "M", modifier = Modifier.align(Alignment.Center))
            }
        },
        title = { Text("Jetpack Compose Explorer") },
    )
}

@Preview
@Composable
fun PreviewTopAppBar() {
    ExploreTopAppBar(state = rememberScaffoldState())
}
