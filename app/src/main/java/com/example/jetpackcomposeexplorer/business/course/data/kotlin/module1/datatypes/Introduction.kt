package com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.datatypes

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics.Basics
import com.example.jetpackcomposeexplorer.business.course.LessonDataImpl
import com.example.jetpackcomposeexplorer.business.course.LessonPage

object Introduction : LessonDataImpl(
    id = DataTypes.id + ".introduction",
    title = "Data types",
    chapter = DataTypes,

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