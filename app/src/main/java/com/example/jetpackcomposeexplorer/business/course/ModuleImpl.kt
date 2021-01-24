package com.example.jetpackcomposeexplorer.business.course

open class ModuleImpl(
    override val id: String,
    override val title: String,
    override val theme: Theme,
) : Module
