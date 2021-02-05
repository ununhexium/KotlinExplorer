package com.example.jetpackcomposeexplorer.injection

import android.app.Application
import androidx.room.Room
import com.example.jetpackcomposeexplorer.BaseApplication
import com.example.jetpackcomposeexplorer.business.domain.LessonProgress
import com.example.jetpackcomposeexplorer.framework.db.ExplorerDatabase
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressDao
import com.example.jetpackcomposeexplorer.framework.db.LessonProgressEntity
import com.example.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperFromEntity
import com.example.jetpackcomposeexplorer.framework.db.mappers.LessonProgressMapperToEntity
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
object DatabaseModule {

  @Singleton
  @Provides
  fun provideExplorerDatabase(
      app: Application
  ): ExplorerDatabase {
    return Room
        .databaseBuilder(app, ExplorerDatabase::class.java, ExplorerDatabase.DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()
  }

  @Singleton
  @Provides
  fun provideLessonProgressDao(
      db: ExplorerDatabase,
  ): LessonProgressDao =
      db.getLessonProgressDao()

  @Singleton
  @Provides
  fun provideLessonProgressMapperFromEntity(): FromEntity<LessonProgressEntity, LessonProgress> =
      LessonProgressMapperFromEntity()

  @Singleton
  @Provides
  fun provideLessonProgressMapperToEntity(): ToEntity<LessonProgressEntity, LessonProgress> =
      LessonProgressMapperToEntity()
}
