package com.example.jetpackcomposeexplorer.framework.ui.frame

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmallVerticalSpacer() = Spacer(Modifier.height(4.dp))

@Composable
fun DefaultVerticalSpacer() = Spacer(Modifier.height(8.dp))

@Composable
fun MediumVerticalSpacer() = Spacer(Modifier.height(16.dp))

@Composable
fun BigVerticalSpacer() = Spacer(Modifier.height(32.dp))

@Composable
fun SmallHorizontalSpacer() = Spacer(Modifier.width(4.dp))

@Composable
fun DefaultHorizontalSpacer() = Spacer(Modifier.width(8.dp))

@Composable
fun MediumHorizontalSpacer() = Spacer(Modifier.width(16.dp))

@Composable
fun BigHorizontalSpacer() = Spacer(Modifier.width(32.dp))