package com.example.jetpackcomposeexplorer.framework.util

interface FromEntity<Entity, DomainModel> {
  operator fun invoke(entity: Entity): DomainModel
}
