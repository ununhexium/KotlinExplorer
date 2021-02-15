package net.lab0.kotlinexplorer.business.course.data.kotlin.basics

import net.lab0.kotlinexplorer.business.domain.ChapterImpl

object Basics : ChapterImpl(
    "kotlin.basics",
    "Basics",
    "Printing, smallest program, variables",
    listOf(
        HelloWorld,
        SmallestProgram
    )
)
