package com.example.jetpackcomposeexplorer.business.course.data.kotlin

import com.example.jetpackcomposeexplorer.business.domain.ModuleImpl
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.basics.Basics
import com.example.jetpackcomposeexplorer.business.course.data.kotlin.datatypes.DataTypes

object Module1: ModuleImpl(
    id = "module1",
    title = "Kotlin Module 1",
    chapters = listOf(
        Basics,
        DataTypes
    )
)