package net.lab0.kotlinexplorer.framework.presentation.composable.frame

val lesson1 = LessonListItemData("lesson1", "Lesson 1", true, highlighted = false, 1f)
val lesson2 = LessonListItemData("lesson2", "Lesson 2", true, highlighted = false, 0.7f)
val lesson3 = LessonListItemData("lesson3", "Lesson 3", false, highlighted = true, 0.1f)
val lesson3b = LessonListItemData("lesson3b", "Lesson 3", false, progress = null)
val lesson3c = LessonListItemData("lesson3c", "Lesson 3", true, progress = null)

val lesson4 = LessonListItemData("lesson4", "Lesson 4", true)
val lesson5 = LessonListItemData("lesson5", "Lesson 5", true)
val lesson6 = LessonListItemData("lesson6", "Lesson 6", false)

val dummyChapter1 =
    ChapterCardData(
        "chapter1",
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
        "chapter2",
        "Chapter 2",
        0.66f,
        listOf(
            lesson4,
            lesson5,
            lesson6,
        )
    )
