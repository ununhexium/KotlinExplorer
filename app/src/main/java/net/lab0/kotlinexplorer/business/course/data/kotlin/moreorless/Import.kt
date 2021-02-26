package net.lab0.kotlinexplorer.business.course.data.kotlin.moreorless

import net.lab0.kotlinexplorer.business.domain.LessonImpl
import net.lab0.kotlinexplorer.business.domain.LessonPage
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks
import net.lab0.kotlinexplorer.business.domain.parser.KotlinCodeWithBlanks.Companion.placeholder as p

object Import : LessonImpl(
    id = "kotlin.moreorless.import",
    title = "Import",
    pages = listOf(
LessonPage.CodeQuestionPage(
            title = "Import",
            question = """
Import the random number generator.
""",
            snippet = """
${p(0)} kotlin.random.Random
""",
            explanation = """
`import` imports the element mentioned right after itself.

All the elements that you use in Kotlin come from some package.
Here we explicitly import the Random object. (More later about what an object really is)

For the moment, you just need to know that they contains features
that you can use instead of re-implementing them by yourself.
""",
            answer = listOf(),
            confusion = listOf(),
        ),
    ),
    dependencies = listOf()
)