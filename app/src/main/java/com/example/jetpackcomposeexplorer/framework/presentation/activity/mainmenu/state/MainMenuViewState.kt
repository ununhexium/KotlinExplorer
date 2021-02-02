package com.example.jetpackcomposeexplorer.framework.presentation.activity.mainmenu.state

import com.example.jetpackcomposeexplorer.business.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.course.implementation.ChapterImpl

data class MainMenuViewState(
    var chapter: Chapter = ChapterImpl("", "", "", listOf()),
)
