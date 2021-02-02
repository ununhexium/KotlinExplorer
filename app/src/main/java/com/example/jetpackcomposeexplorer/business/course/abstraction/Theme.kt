package com.example.jetpackcomposeexplorer.business.course.abstraction

interface Theme : Prerequisite {
  val modules: List<Module>
}
