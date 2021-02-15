package net.lab0.kotlinexplorer.framework.util

interface ToEntity<Entity, DomainModel> {
  operator fun invoke(domain: DomainModel): Entity
}
