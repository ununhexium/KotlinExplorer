package com.example.jetpackcomposeexplorer.business.course.implementation

import com.example.jetpackcomposeexplorer.business.course.abstraction.Chapter
import com.example.jetpackcomposeexplorer.business.course.abstraction.Module

open class ModuleImpl(
    override val id: String,
    override val title: String,
    override val chapters: List<Chapter>,
) : Module
