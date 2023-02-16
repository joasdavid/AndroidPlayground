package pt.joasvpereira.sessionfeature.presentation.select.session

import pt.joasvpereira.sessionfeature.domain.data.SessionItem

data class SelectSessionFeatureState(
    val sessions : List<SessionItem>? = null,
    val isLoading : Boolean = false,
    val isEditMode : Boolean = false
)
