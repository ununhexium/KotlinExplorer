package com.example.jetpackcomposeexplorer.business.course

import com.example.jetpackcomposeexplorer.business.course.data.FakeGenerator
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics.HelloWorld
import com.example.jetpackcomposeexplorer.business.course.implementation.CourseRepositoryImpl
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CourseRepositoryImplTest {
  val fakeTheme = FakeGenerator.generateFakeTheme()
  val courseRepositoryImpl = CourseRepositoryImpl(listOf(fakeTheme))

  @Test
  fun `can list an existing lesson`() {
    // given
    val firstLesson = fakeTheme.modules.first().chapters.first().lessons.first()
    val id: String = firstLesson.id

    // when
    val lesson = courseRepositoryImpl.findLessonById(id)

    // then
    assertThat(lesson).isSameInstanceAs(firstLesson)
  }

  @Test
  fun `can find the next chapter`() {
    // given
    val firstChapter = fakeTheme.modules.first().chapters.drop(0).first()
    val secondChapter = fakeTheme.modules.first().chapters.drop(1).first()
    val thirdChapter = fakeTheme.modules.first().chapters.drop(2).first()

    // when
    val chapter2 = courseRepositoryImpl.findNextChapter(firstChapter)
    val noChapter = courseRepositoryImpl.findNextChapter(thirdChapter)

    // then
    assertThat(chapter2).isSameInstanceAs(secondChapter)
    assertThat(noChapter).isNull()
  }
}
