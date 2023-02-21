package pt.joasvpereira.xorganizer.presentation.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.presentation.MainFeatureEntryPoint
import com.joasvpereira.settings.presentation.main.SettingsMainMenuScreen
import org.koin.androidx.compose.getViewModel
import pt.joasvpereira.core.navigation.navigateAndResetStack
import pt.joasvpereira.core.repository.CurrentSession
import pt.joasvpereira.sessionfeature.compose.navigation.composableCreateSession
import pt.joasvpereira.sessionfeature.compose.navigation.navigateToUpdateProfile
import pt.joasvpereira.sessionfeature.presentation.SessionFeatureEntryPoint

sealed class MainNavGraphRoutes(val route: String) {
    object ProfileFeature: MainNavGraphRoutes(route = PROFILE_ROUTE)

    object MainFeature: MainNavGraphRoutes(route = MAIN_ROUTE)

    object SettingsFeature: MainNavGraphRoutes(route = SETTINGS_ROUTE)

    companion object {
        private const val PROFILE_ROUTE = "profile_feature_route"

        private const val SETTINGS_ROUTE = "settings_feature_route"

        private const val MAIN_ROUTE = "main_feature_route"
    }
}

fun NavController.navigateToProfileFeature() {
    navigateAndResetStack(MainNavGraphRoutes.ProfileFeature.route)
}

fun NavController.navigateToDivisionsFeature() {
    navigateAndResetStack(MainNavGraphRoutes.MainFeature.route)
}

fun NavController.navigateToSettingsFeature() {
    navigate(MainNavGraphRoutes.SettingsFeature.route)
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    CurrentSession.sessionId.logThis(tag = "JVP") { "Current session = $it" }
    val start = if (CurrentSession.sessionId == null) MainNavGraphRoutes.ProfileFeature.route else MainNavGraphRoutes.MainFeature.route
    NavHost(
        navController = navController,
        startDestination = start.logThis(tag = "JVP")
    ) {
        composable(MainNavGraphRoutes.ProfileFeature.route) {
            SessionFeatureEntryPoint(
                onFinished = { navController.navigateToDivisionsFeature() }
            )
        }

        composable(MainNavGraphRoutes.MainFeature.route) {
            MainFeatureEntryPoint(onSettingsClicked = {
                //navController.navigateToProfileFeature()
                navController.navigateToSettingsFeature()
            })
        }

        composableCreateSession(navController)

        composable(MainNavGraphRoutes.SettingsFeature.route) {
            SettingsMainMenuScreen(
                viewModel = getViewModel(), navController = navController, onEditProfile = {
                    CurrentSession.sessionId?.run { navController.navigateToUpdateProfile(id) }
                },
                onLogout = {navController.navigateToProfileFeature()}
                )
        }
    }
}