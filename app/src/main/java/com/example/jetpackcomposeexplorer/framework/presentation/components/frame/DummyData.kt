package com.example.jetpackcomposeexplorer.framework.presentation.components.frame

val lesson1 = LessonListItemData("lesson1","Lesson 1", true)
val lesson2 = LessonListItemData("lesson2","Lesson 2", false)
val lesson3 = LessonListItemData("lesson3","Lesson 3", false)

val lesson4 = LessonListItemData("lesson4","Lesson 4", true)
val lesson5 = LessonListItemData("lesson5","Lesson 5", true)
val lesson6 = LessonListItemData("lesson6","Lesson 6", false)

val dummyChapter1 =
    ChapterCardData(
        "Chapter 1",
        0.33f,
        listOf(
            lesson1,
            lesson2,
            lesson3,
        )
    )

val dummyChapter2 =
    ChapterCardData(
        "Chapter 2",
        0.66f,
        listOf(
            lesson4,
            lesson5,
            lesson6,
        )
    )
