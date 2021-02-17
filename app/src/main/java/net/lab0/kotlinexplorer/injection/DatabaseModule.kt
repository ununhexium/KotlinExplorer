package net.lab0.kotlinexplorer.injection

import android.app.Application
import androidx.room.Room
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.framework.db.ExplorerDatabase
import net.lab0.kotlinexplorer.framework.db.LessonProgressDao
import net.lab0.kotlinexplorer.framework.db.LessonProgressEntity
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperToModel
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperFromModel
import net.lab0.kotlinexplorer.framework.util.ToModel
import net.lab0.kotlinexplorer.framework.util.FromModel
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
  fun provideLessonProgressMapperFromEntity(): ToModel<LessonProgressEntity, LessonProgress> =
      LessonProgressMapperToModel()

  @Singleton
  @Provides
  fun provideLessonProgressMapperToEntity(): FromModel<LessonProgressEntity, LessonProgress> =
      LessonProgressMapperFromModel()
}
