package net.lab0.kotlinexplorer.framework.presentation.activity.lesson

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import net.lab0.kotlinexplorer.framework.presentation.activity.lesson.mvi.LessonViewModel

class LessonActivity: AppCompatActivity() {
  private val lessonViewModel: LessonViewModel by viewModels()
}
