package com.example.jetpackcomposeexplorer.business.domain

data class Lesson(
    val id: String,
    val title: String,
    val completed: Boolean,
    // TODO: add answers success rate
)
