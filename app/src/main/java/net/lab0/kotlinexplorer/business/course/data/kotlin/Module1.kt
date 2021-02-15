package net.lab0.kotlinexplorer.business.course.data.kotlin

import net.lab0.kotlinexplorer.business.domain.ModuleImpl
import net.lab0.kotlinexplorer.business.course.data.kotlin.basics.Basics
import net.lab0.kotlinexplorer.business.course.data.kotlin.datatypes.DataTypes

object Module1: ModuleImpl(
    id = "module1",
    title = "Kotlin Module 1",
    chapters = listOf(
        Basics,
        DataTypes
    )
)
