package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.ChapterImpl

object PocketCalculatorChapter : ChapterImpl(
  id = "kotlin.pocketcalculator",
  title = "Pocket calculator",
  description = "Basic maths and function calls",
  lessons = listOf(
    Introduction,
    Function,
    StringSpecialChars,
    PriorityForSignAndMultiplicativeOperators,
    PriorityForAdditiveOperators,
    PriorityForComparisonAndSimpleAssignmentOperators,
    PriorityFinalBoss,
  )
)
