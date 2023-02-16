package pt.joasvpereira.xorganizer.presentation.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.main.presentation.MainFeatureEntryPoint
import pt.joasvpereira.core.navigation.navigateAndResetStack
import pt.joasvpereira.sessionfeature.CurrentSession
import pt.joasvpereira.sessionfeature.presentation.SessionFeatureEntryPoint

sealed class MainNavGraphRoutes(val route: String) {
    object ProfileFeature: MainNavGraphRoutes(route = PROFILE_ROUTE)

    object MainFeature: MainNavGraphRoutes(route = MAIN_ROUTE)

    companion object {
        private const val PROFILE_ROUTE = "profile_feature_route"

        private const val MAIN_ROUTE = "main_feature_route"
    }
}

fun NavController.navigateToProfileFeature() {
    navigateAndResetStack(MainNavGraphRoutes.ProfileFeature.route)
}

fun NavController.navigateToDivisionsFeature() {
    navigateAndResetStack(MainNavGraphRoutes.MainFeature.route)
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    CurrentSession.session.logThis(tag = "JVP") { "Current session = $it" }
    val start = if(CurrentSession.session == null) MainNavGraphRoutes.ProfileFeature.route else MainNavGraphRoutes.MainFeature.route
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
            MainFeatureEntryPoint(onSwitchProfile = {
                navController.navigateToProfileFeature()
            })
        }
    }
}