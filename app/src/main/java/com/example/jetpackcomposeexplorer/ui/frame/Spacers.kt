package com.example.jetpackcomposeexplorer.ui.frame

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SmallVerticalSpacer() = Spacer(Modifier.height(11.dp))

@Composable
fun DefaultVerticalSpacer() = Spacer(Modifier.height(17.dp))

@Composable
fun MediumVerticalSpacer() = Spacer(Modifier.height(29.dp))

@Composable
fun BigVerticalSpacer() = Spacer(Modifier.height(46.dp))

@Composable
fun SmallHorizontalSpacer() = Spacer(Modifier.width(11.dp))

@Composable
fun DefaultHorizontalSpacer() = Spacer(Modifier.width(17.dp))

@Composable
fun MediumHorizontalSpacer() = Spacer(Modifier.width(29.dp))

@Composable
fun BigHorizontalSpacer() = Spacer(Modifier.width(46.dp))