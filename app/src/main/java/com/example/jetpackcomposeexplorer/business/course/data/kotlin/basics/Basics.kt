package com.example.jetpackcomposeexplorer.business.course.data.kotlin.basics

import com.example.jetpackcomposeexplorer.business.domain.ChapterImpl

object Basics : ChapterImpl(
    "kotlin.basics",
    "Basics",
    "Printing, smallest program, variables",
    listOf(
        HelloWorld,
        SmallestProgram
    )
)
