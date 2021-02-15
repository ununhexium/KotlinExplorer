package net.lab0.kotlinexplorer.business.data

import net.lab0.kotlinexplorer.business.domain.Module
import net.lab0.kotlinexplorer.business.course.data.kotlin.Module1

class KotlinModuleRepository {
  fun getKotlinModules(): List<Module> {
    return listOf(
        Module1
    )
  }
}
