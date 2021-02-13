package net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.oddeven

import net.lab0.jetpackcomposeexplorer.business.domain.ChapterImpl

object OddEvenChapter: ChapterImpl(
    id = "kotlin.oddeven",
    title = "Odd or Even",
    description = "Tell if a number is odd or even",
    lessons = listOf(
        Value,
        Boolean,
        DataType,
    )
)
