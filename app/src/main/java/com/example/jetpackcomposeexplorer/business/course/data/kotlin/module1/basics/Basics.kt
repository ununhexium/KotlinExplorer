package com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics

import com.example.jetpackcomposeexplorer.business.course.implementation.ChapterImpl


val Basics = ChapterImpl(
    id = "kotlin.module1.basics",
    title = "Basics",
    description = "Printing, smallest program, variables",
    lessons = listOf(
        HelloWorld,
        SmallestProgram,
    )
)
