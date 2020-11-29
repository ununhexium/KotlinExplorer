package com.example.jetpackcomposeexplorer.model

data class PageID(val name: String, val parentPageID: PageID? = null)
