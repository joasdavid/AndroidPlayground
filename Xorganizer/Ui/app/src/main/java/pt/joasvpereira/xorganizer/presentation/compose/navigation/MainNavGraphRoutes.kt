package pt.joasvpereira.xorganizer.presentation.compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joasvpereira.main.presentation.MainFeatureEntryPoint
import pt.joasvpereira.core.navigation.navigateAndResetStack
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
    NavHost(
        navController = navController,
        startDestination = MainNavGraphRoutes.ProfileFeature.route
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