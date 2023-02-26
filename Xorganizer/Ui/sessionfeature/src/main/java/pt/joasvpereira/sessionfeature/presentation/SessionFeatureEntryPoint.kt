package pt.joasvpereira.sessionfeature.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import pt.joasvpereira.sessionfeature.compose.navigation.START_DISTINCTION
import pt.joasvpereira.sessionfeature.compose.navigation.composableCreateSession
import pt.joasvpereira.sessionfeature.compose.navigation.composableSelectProfile

@Composable
fun SessionFeatureEntryPoint(
    onFinished: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = START_DISTINCTION) {
        composableSelectProfile(navController, onProfileSelected = { onFinished() })
        composableCreateSession(navController)
    }
}
