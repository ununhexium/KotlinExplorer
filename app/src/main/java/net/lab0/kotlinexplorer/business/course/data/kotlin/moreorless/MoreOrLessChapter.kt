package net.lab0.kotlinexplorer.business.course.data.kotlin.moreorless

import net.lab0.kotlinexplorer.business.domain.ChapterImpl

object MoreOrLessChapter: ChapterImpl(
    id ="kotlin.moreorless",
    title = "More or less",
    description = "Guess the number: the game.",
    lessons = listOf(
        Introduction
    )
)