package net.lab0.kotlinexplorer.business.domain

open class ModuleImpl(
  override val title: String,
  override val chapters: List<Chapter>,
  override val id: String,
) : Module
