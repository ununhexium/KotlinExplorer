package net.lab0.kotlinexplorer.business.course.data.kotlin.helloworld

import net.lab0.kotlinexplorer.business.domain.ChapterImpl

object HelloWorldChapter
  : ChapterImpl(
    id = "kotlin.helloworld",
    title = "Hello World",
    description = "The classic beginner's program",
    lessons = listOf(
        Introduction,
        String,
        Print,
        Comment,
        Main,
        Indentation,
        HelloWorld,
    )
)
