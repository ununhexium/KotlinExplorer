package net.lab0.kotlinexplorer.framework.util

interface ToModel<Entity, DomainModel> {
  operator fun invoke(entity: Entity): DomainModel
}
