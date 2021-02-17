package net.lab0.kotlinexplorer.framework.util

interface ToDomain<Entity, DomainModel> {
  operator fun invoke(entity: Entity): DomainModel
}
