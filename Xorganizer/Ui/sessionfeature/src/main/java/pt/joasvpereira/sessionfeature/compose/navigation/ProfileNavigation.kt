package pt.joasvpereira.sessionfeature.compose.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.koin.androidx.compose.getViewModel
import pt.joasvpereira.core.repository.CurrentSession
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.presentation.create.CreateSessionFeatureScreen
import pt.joasvpereira.sessionfeature.presentation.select.session.SelectSessionFeatureScreen

//region Profile selection navigation

internal fun NavController.navigateToSelectProfile() {
    navigate(SELECT_PROFILE_ROUTE)
}

private const val SELECT_PROFILE_ROUTE = "select_profile"
fun NavGraphBuilder.composableSelectProfile(
    navController: NavController,
    onProfileSelected: (session: SessionItem) -> Unit
) {
    composable(
        SELECT_PROFILE_ROUTE
    ) {
        SelectSessionFeatureScreen(getViewModel(), navController = navController) {
            CurrentSession.session = it
            onProfileSelected(it)
        }
    }
}
//endregion

//region Create or update profile navigation
private const val CREATE_OR_UPDATE_PARAM_ID = "id"
private const val CREATE_OR_UPDATE_BASE_ROUTE = "create_or_update_profile"

internal fun NavController.navigateToCreateProfile() {
    navigate(CREATE_OR_UPDATE_BASE_ROUTE)
}

internal fun NavController.navigateToUpdateProfile(profileId : Int) {
    navigate("$CREATE_OR_UPDATE_BASE_ROUTE?$CREATE_OR_UPDATE_PARAM_ID=$profileId")
}

private const val CREATE_OR_UPDATE_FULL_ROUTE = "$CREATE_OR_UPDATE_BASE_ROUTE?$CREATE_OR_UPDATE_PARAM_ID={$CREATE_OR_UPDATE_PARAM_ID}"
internal fun NavGraphBuilder.composableCreateSession(navController: NavController) {
    composable(CREATE_OR_UPDATE_FULL_ROUTE,
        arguments = listOf(navArgument(CREATE_OR_UPDATE_PARAM_ID) { defaultValue = -1 })
    ) {
        val id = it.arguments?.getInt(CREATE_OR_UPDATE_PARAM_ID)
        CreateSessionFeatureScreen(id, getViewModel(), navController = navController)
    }
}
//endregion

//region Start destinations
internal const val START_DISTINCTION = SELECT_PROFILE_ROUTE
//endregion
