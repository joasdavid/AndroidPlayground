package pt.joasvpereira.xorganizer.presentation.mapper

interface BaseMapper<PRESENTATION, DOMAIN> {
    fun mapToPresentation(from: DOMAIN): PRESENTATION
    fun mapToDomain(from: PRESENTATION): DOMAIN
}