package com.example.jetpackcomposeexplorer.business.util

interface EntityMapper <Entity, DomainModel>{
    fun fromEntity(entity: Entity): DomainModel
    fun toEntity(domainModel: DomainModel): Entity
}
