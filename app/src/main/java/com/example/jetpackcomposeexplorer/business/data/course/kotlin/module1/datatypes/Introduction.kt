package com.example.jetpackcomposeexplorer.business.data.course.kotlin.module1.datatypes

import com.example.jetpackcomposeexplorer.business.data.course.implementation.LessonDataImpl
import com.example.jetpackcomposeexplorer.business.data.course.implementation.LessonPage
import com.example.jetpackcomposeexplorer.business.data.course.kotlin.module1.basics.Basics

val Introduction = LessonDataImpl(
    id = "kotlin.module1.datatypes.introduction",
    title = "Data types",

    dependencies = listOf(
        Basics
    ),
    pages = listOf(
        LessonPage.InfoPage(
            "Data types",
            """
Kotlin is a typed language. This means that each element that you manipulate will have an associated type.

This type will:
* tell you what you can do with a value
* let the compiler know if you made any obvious in your program
* choose the most appropriate operation to execute
"""
        )
    )
)
