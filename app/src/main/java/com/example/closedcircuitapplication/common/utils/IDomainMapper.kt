package com.example.closedcircuitapplication.common.utils

interface IDomainMapper<Dto, DomainModel> {

    fun mapToDomain(entity: Dto): DomainModel

}