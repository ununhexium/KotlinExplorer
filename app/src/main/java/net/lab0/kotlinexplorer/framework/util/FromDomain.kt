package net.lab0.kotlinexplorer.framework.util

interface FromDomain<Entity, DomainModel> {
  operator fun invoke(domain: DomainModel): Entity
}
