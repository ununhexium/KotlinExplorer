package com.example.jetpackcomposeexplorer.presentation.components.frame

val lesson1 = LessonListItemData("Lesson 1", true)

val lesson2 = LessonListItemData("Lesson 2", false)

val lesson3 = LessonListItemData("Lesson 3", false)

val dummyChapter =
    ChapterCardData(
        "The title",
        0.33f,
        listOf(
            lesson1,
            lesson2,
            lesson3,
        )
    )