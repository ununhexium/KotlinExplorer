package com.example.jetpackcomposeexplorer.business.course

open class ThemeImpl(
    override val id: String,
    override val title: String,
    override val modules: List<Module>,
) : Theme
