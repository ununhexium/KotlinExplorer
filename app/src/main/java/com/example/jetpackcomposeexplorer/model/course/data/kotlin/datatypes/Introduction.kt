package com.example.jetpackcomposeexplorer.model.course.data.kotlin.datatypes

import com.example.jetpackcomposeexplorer.model.course.LessonImpl
import com.example.jetpackcomposeexplorer.model.course.LessonPage.InfoPage
import com.example.jetpackcomposeexplorer.model.course.data.kotlin.basics.Basics

object Introduction : LessonImpl(
    id = "kotlin.datatypes.introduction",
    title = "Data types",
    dependencies = listOf(
        Basics
    ),
    pages = listOf(
        InfoPage(
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
