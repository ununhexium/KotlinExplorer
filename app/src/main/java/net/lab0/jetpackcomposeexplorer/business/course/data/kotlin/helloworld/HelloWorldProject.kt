package net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.helloworld

import net.lab0.jetpackcomposeexplorer.business.domain.ChapterImpl

object HelloWorldProject: ChapterImpl(
    id = "kotlin.helloworld",
    title = "Hello World",
    description = "The classic beginner's program",
    lessons = listOf(
        String,
        Print,
        Comment,
        Main,
        HelloWorld,
    )
)
