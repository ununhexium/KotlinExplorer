package com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics

import com.example.jetpackcomposeexplorer.business.course.ChapterImpl
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.Module1


val Basics = ChapterImpl(
    id = "kotlin.module1.basics",
    title = "Basics",
    description = "Printing, smallest program, variables",
    lessons = listOf(
        HelloWorld,
        SmallestProgram,
    )
)
