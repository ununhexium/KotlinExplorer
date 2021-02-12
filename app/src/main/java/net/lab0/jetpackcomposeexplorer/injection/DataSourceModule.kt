package net.lab0.jetpackcomposeexplorer.injection

import net.lab0.jetpackcomposeexplorer.business.domain.LessonProgress
import net.lab0.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import net.lab0.jetpackcomposeexplorer.business.persistence.implementation.LessonProgressDataSourceImpl
import net.lab0.jetpackcomposeexplorer.framework.db.LessonProgressDao
import net.lab0.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import net.lab0.jetpackcomposeexplorer.framework.util.FromEntity
import net.lab0.jetpackcomposeexplorer.framework.util.ToEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@InstallIn(ApplicationComponent::class)
@Module
object DataSourceModule {
  @Singleton
  @Provides
  fun provideLessonProgressDataSource(
      dao: LessonProgressDao,
      // TODO mappers should go in a DaoService class
      fromEntity: FromEntity<LessonProgressEntity, LessonProgress>,
      toEntity: ToEntity<LessonProgressEntity, LessonProgress>,
  ): LessonProgressDataSource {
    return LessonProgressDataSourceImpl(dao, fromEntity, toEntity)
  }
}
