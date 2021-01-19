package com.example.jetpackcomposeexplorer.model.course.data

import com.example.jetpackcomposeexplorer.model.KotlinCodeWithBlanks.Companion.placeholder
import com.example.jetpackcomposeexplorer.presentation.ui.codequestion.CodeQuestionPage

val steps = listOf(
    CodeQuestionPage(
        "",
        """println("${placeholder(0)}")""",
        "α Alpha, β beta, γ gamma, ...",
        1,
        "alpha", "beta", "gamma"
    ) {
      it.first().text == "alpha"
    }
)

