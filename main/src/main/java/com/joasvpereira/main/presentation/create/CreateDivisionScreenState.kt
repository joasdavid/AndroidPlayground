package com.joasvpereira.main.presentation.create

import com.joasvpereira.main.presentation.icons.DivisionIcon
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.theme.ThemeOption

data class CreateDivisionScreenState(
    val icon: DivisionIcon = DivisionIcons.all.first(),
    val name: String = "",
    val description: String = "",
    val theme: ThemeOption = ThemeOption.THEME_DEFAULT,
    val isLoading: Boolean = false,
    val mode: Mode = Mode.CREATE,
    val navigation: CreateDivisionScreenNavigation = CreateDivisionScreenNavigation.Idle,
    val deleteData: DeleteData = DeleteData("", "", false),
)

data class DeleteData(
    val name: String,
    val matchingName: String,
    val isPopupShowing: Boolean,
) {
    val isNameMatchingConfirmation: Boolean
        get() = name == matchingName
}

sealed interface CreateDivisionScreenNavigation {
    object Idle : CreateDivisionScreenNavigation
    object SaveDone : CreateDivisionScreenNavigation
    object DeleteDone : CreateDivisionScreenNavigation
}

enum class Mode {
    CREATE,
    EDIT,
}
