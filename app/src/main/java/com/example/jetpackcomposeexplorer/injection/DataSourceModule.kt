package com.example.jetpackcomposeexplorer.injection

import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.business.persistence.abstraction.LessonProgressDataSource
import com.example.jetpackcomposeexplorer.business.persistence.implementation.LessonProgressDataSourceImpl
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressDao
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import com.example.jetpackcomposeexplorer.framework.util.FromEntity
import com.example.jetpackcomposeexplorer.framework.util.ToEntity
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
