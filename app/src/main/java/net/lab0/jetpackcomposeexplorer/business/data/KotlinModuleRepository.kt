package net.lab0.jetpackcomposeexplorer.business.data

import net.lab0.jetpackcomposeexplorer.business.domain.Module
import net.lab0.jetpackcomposeexplorer.business.course.data.kotlin.Module1

class KotlinModuleRepository {
  fun getKotlinModules(): List<Module> {
    return listOf(
        Module1
    )
  }
}
