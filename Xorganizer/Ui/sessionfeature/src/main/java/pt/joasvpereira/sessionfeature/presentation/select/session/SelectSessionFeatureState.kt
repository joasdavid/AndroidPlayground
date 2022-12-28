package pt.joasvpereira.sessionfeature.presentation.select.session

import pt.joasvpereira.sessionfeature.domain.data.SessionItem

data class SelectSessionFeatureState(
    val sessions : List<SessionItem> = emptyList(),
    val isLoading : Boolean = false
)
