package com.example.jetpackcomposeexplorer.framework.util

interface ToEntity<Entity, DomainModel> {
  operator fun invoke(domain: DomainModel): Entity
}
