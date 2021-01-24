package com.example.jetpackcomposeexplorer.business.course.data.kotlin

import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.Module1
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics.Basics
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics.HelloWorld
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics.SmallestProgram
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.datatypes.DataTypes
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LessonFinderImplTest {
  @Test
  fun `can list an existing lesson`() {
    // given
    val id = HelloWorld.id

    // when
    val lesson = LessonFinderImpl.findLessonById(id)

    // then
    assertThat(lesson).isSameInstanceAs(HelloWorld)
  }

  @Test
  fun `can find lessons in chapter`() {
    // given
    val chapter= Basics

    // when
    val lessons = LessonFinderImpl.findLessonsInChapter(chapter)

    // then
    assertThat(lessons).containsAtLeast(HelloWorld, SmallestProgram)
  }

  @Test
  fun `can find chapters in module`() {
    // given
    val module = Module1

    // when
    val chapters = LessonFinderImpl.findChaptersInModule(module)

    // then
    assertThat(chapters).containsAtLeast(Basics, DataTypes)
  }
}
