package net.lab0.jetpackcomposeexplorer.framework.util

interface ToEntity<Entity, DomainModel> {
  operator fun invoke(domain: DomainModel): Entity
}
