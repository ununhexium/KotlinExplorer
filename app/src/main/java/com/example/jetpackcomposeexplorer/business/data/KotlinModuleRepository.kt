package com.example.jetpackcomposeexplorer.business.data

import com.example.jetpackcomposeexplorer.business.domain.Module
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.Module1

class KotlinModuleRepository {
  fun getKotlinModules(): List<Module> {
    return listOf(
        Module1
    )
  }
}
