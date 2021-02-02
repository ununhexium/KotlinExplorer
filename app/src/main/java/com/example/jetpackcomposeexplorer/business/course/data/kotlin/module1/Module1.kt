package com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1

import com.example.jetpackcomposeexplorer.business.course.implementation.ModuleImpl
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.basics.Basics
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.module1.datatypes.DataTypes

object Module1 : ModuleImpl(
    id = "kotlin.module1",
    title = "Kotlin Module 1",

    chapters = listOf(
        Basics,
        DataTypes,
    )
)
