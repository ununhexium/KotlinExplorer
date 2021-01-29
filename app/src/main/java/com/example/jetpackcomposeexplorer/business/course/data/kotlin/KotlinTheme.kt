package com.example.jetpackcomposeexplorer.business.course.data.kotlin

import com.example.jetpackcomposeexplorer.business.course.ThemeImpl
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.Module1

object KotlinTheme : ThemeImpl(
    "kotlin",
    "Kotlin",
    listOf(
        Module1,
    )
)
