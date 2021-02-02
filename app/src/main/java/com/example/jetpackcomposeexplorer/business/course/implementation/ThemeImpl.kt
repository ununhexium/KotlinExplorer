package com.example.jetpackcomposeexplorer.business.course.implementation

import com.example.jetpackcomposeexplorer.business.course.abstraction.Module
import com.example.jetpackcomposeexplorer.business.course.abstraction.Theme

open class ThemeImpl(
    override val id: String,
    override val title: String,
    override val modules: List<Module>,
) : Theme
