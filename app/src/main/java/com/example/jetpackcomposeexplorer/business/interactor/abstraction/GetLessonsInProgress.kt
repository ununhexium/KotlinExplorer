package com.example.jetpackcomposeexplorer.business.interactor.abstraction

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress

interface GetLessonsInProgress {
  suspend operator fun invoke(): List<LessonProgress>
}
