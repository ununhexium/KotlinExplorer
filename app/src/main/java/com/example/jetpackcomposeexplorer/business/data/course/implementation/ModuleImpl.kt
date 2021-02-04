package com.example.jetpackcomposeexplorer.business.data.course.implementation

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Module

open class ModuleImpl(
    override val id: String,
    override val title: String,
    override val chapters: List<Chapter>,
) : Module
