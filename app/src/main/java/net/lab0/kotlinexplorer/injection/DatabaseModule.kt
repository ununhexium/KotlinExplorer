package net.lab0.kotlinexplorer.injection

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import net.lab0.kotlinexplorer.business.domain.LessonProgress
import net.lab0.kotlinexplorer.framework.db.ExplorerDatabase
import net.lab0.kotlinexplorer.framework.db.LessonProgressDao
import net.lab0.kotlinexplorer.framework.db.LessonProgressEntity
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperFromDomain
import net.lab0.kotlinexplorer.framework.db.mappers.LessonProgressMapperToDomain
import net.lab0.kotlinexplorer.framework.util.FromDomain
import net.lab0.kotlinexplorer.framework.util.ToDomain
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@FlowPreview
@InstallIn(SingletonComponent::class)
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
  fun provideLessonProgressMapperFromEntity(): ToDomain<LessonProgressEntity, LessonProgress> =
    LessonProgressMapperToDomain()

  @Singleton
  @Provides
  fun provideLessonProgressMapperToEntity(): FromDomain<LessonProgressEntity, LessonProgress> =
    LessonProgressMapperFromDomain()
}
