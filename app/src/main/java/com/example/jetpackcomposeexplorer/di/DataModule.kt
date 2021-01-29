package com.example.jetpackcomposeexplorer.di

import com.example.jetpackcomposeexplorer.business.course.Finder
import com.example.jetpackcomposeexplorer.business.course.FinderImpl
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.KotlinTheme
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {
  @Singleton
  @Provides
  fun provideFinder(): Finder {
    return FinderImpl(KotlinTheme)
  }
}
