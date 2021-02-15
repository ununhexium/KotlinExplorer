package net.lab0.kotlinexplorer.framework.util

interface FromEntity<Entity, DomainModel> {
  operator fun invoke(entity: Entity): DomainModel
}
