package com.example.closedcircuitapplication.common.common.utils

interface IDomainMapper<Dto, DomainModel> {

    fun mapToDomain(entity: Dto): DomainModel

}