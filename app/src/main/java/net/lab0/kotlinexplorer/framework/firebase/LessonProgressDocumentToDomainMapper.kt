package net.lab0.kotlinexplorer.framework.firebase

import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.framework.util.ToDomain

class LessonProgressDocumentToDomainMapper : ToDomain<LessonProgressDocument, LessonProgress> {
  override fun invoke(entity: LessonProgressDocument) = LessonProgress(
      entity.lessonId,
      entity.successCount,
      entity.failureCount,
  )
}
