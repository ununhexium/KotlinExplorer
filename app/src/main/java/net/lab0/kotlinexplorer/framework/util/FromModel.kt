package net.lab0.kotlinexplorer.framework.util

interface FromModel<Entity, DomainModel> {
  operator fun invoke(domain: DomainModel): Entity
}
