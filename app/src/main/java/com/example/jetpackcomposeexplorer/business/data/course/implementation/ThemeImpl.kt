package com.example.jetpackcomposeexplorer.business.data.course.implementation

import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Module
import com.example.jetpackcomposeexplorer.business.data.course.abstraction.Theme

open class ThemeImpl(
    override val id: String,
    override val title: String,
    override val modules: List<Module>,
) : Theme
