package pt.joasvpereira.xorganizer.presentation.mapper

interface BaseMapper<PRESENTATION, DOMAIN> {
    fun mapToPresentation(from: DOMAIN): PRESENTATION

    //fun mapToPresentation(from: List<DOMAIN>): List<PRESENTATION>
    fun mapToDomain(from: PRESENTATION): DOMAIN
    //fun mapToDomain(from: List<PRESENTATION>): List<DOMAIN>
}

fun <PRESENTATION, DOMAIN> BaseMapper<PRESENTATION, DOMAIN>.mapToPresentationList(from: List<DOMAIN>): List<PRESENTATION> =
    from.map { mapToPresentation(it) }

fun <PRESENTATION, DOMAIN> BaseMapper<PRESENTATION, DOMAIN>.mapToDomainList(from: List<PRESENTATION>): List<DOMAIN> =
    from.map { mapToDomain(it) }