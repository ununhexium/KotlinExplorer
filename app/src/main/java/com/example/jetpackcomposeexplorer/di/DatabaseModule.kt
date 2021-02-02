package com.example.jetpackcomposeexplorer.di

import android.content.Context
import androidx.room.Room
import com.example.jetpackcomposeexplorer.business.course.abstraction.CourseRepository
import com.example.jetpackcomposeexplorer.business.domain.Exercise
import com.example.jetpackcomposeexplorer.business.util.EntityMapper
import com.example.jetpackcomposeexplorer.framework.datasource.database.ExplorerDatabase
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonDao
import com.example.jetpackcomposeexplorer.framework.datasource.database.LessonEntity
import com.example.jetpackcomposeexplorer.framework.datasource.mapper.LessonMapper
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoService
import com.example.jetpackcomposeexplorer.framework.datasource.service.LessonDaoServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

  @Singleton
  @Provides
  fun provideLessonMapper(courseRepository: CourseRepository): EntityMapper<LessonEntity, Exercise> {
    return LessonMapper(courseRepository)
  }

  @Singleton
  @Provides
  fun provideLessonDao(database: ExplorerDatabase): LessonDao {
    return database.lessonDao()
  }

  @Singleton
  @Provides
  fun provideExplorerDatabase(@ApplicationContext context: Context): ExplorerDatabase {
    return Room
        .databaseBuilder(
            context,
            ExplorerDatabase::class.java,
            "jetpack-explorer"
        )
        .build()
  }

  @Singleton
  @Provides
  fun provideLessonDaoService(
      lessonDao: LessonDao,
      lessonMapper: LessonMapper,
  ): LessonDaoService {
    return LessonDaoServiceImpl(lessonDao, lessonMapper)
  }
}
