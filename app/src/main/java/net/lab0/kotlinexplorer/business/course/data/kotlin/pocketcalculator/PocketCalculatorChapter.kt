package net.lab0.kotlinexplorer.business.course.data.kotlin.pocketcalculator

import net.lab0.kotlinexplorer.business.domain.ChapterImpl

object PocketCalculatorChapter : ChapterImpl(
  id = "kotlin.pocketcalculator",
  title = "Pocket calculator",
  description = "Basic maths and function calls",
  lessons = listOf(
    Introduction,
    FunctionDeclarationAndCall,
    FunctionReturn,
    StringSpecialChars, // TODO: needed? move to later?
    PriorityForSignAndMultiplicativeOperators,
    PriorityForAdditiveOperators,
    PriorityForComparisonAndSimpleAssignmentOperators,
    PriorityFinalBoss,
    Integers,
    FloatingPoint,
    DoublePrecision,
    PocketCalculatorProject,
  )
// TODO after it's be stated what a member / field is, show the problems of ${abc.xyz) in string templates
)
