package com.example.jetpackcomposeexplorer.business.data.course.kotlin

import com.example.jetpackcomposeexplorer.business.data.course.implementation.ThemeImpl
import com.example.jetpackcomposeexplorer.business.data.course.kotlin.module1.Module1

object KotlinTheme : ThemeImpl(
    "kotlin",
    "Kotlin",
    listOf(
        Module1,
    )
)
