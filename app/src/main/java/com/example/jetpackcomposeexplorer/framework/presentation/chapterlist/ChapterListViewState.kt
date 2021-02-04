package com.example.jetpackcomposeexplorer.framework.presentation.chapterlist

import com.example.jetpackcomposeexplorer.framework.presentation.chapterlist.model.Chapter

data class ChapterListViewState(
    var chapters: List<Chapter>? = null,
)
