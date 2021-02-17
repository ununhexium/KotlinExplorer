package net.lab0.kotlinexplorer.business.course.data.kotlin.positivenegative

import net.lab0.kotlinexplorer.business.domain.ChapterImpl

object PositiveNegativeChapter : ChapterImpl(
    id = "kotlin.positivenegative",
    title = "+ or -",
    description = "Tell if a number is positive or negative",
    lessons = listOf(
        Value,
        Boolean,
        DataType,
        Comparators,
        StringConcatenation,
        IfElse,
    )
)
