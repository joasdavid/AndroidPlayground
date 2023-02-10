package pt.joasvpereira.sessionfeature.presentation

import android.content.IntentSender.OnFinished
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.sessionfeature.CurrentSession
import pt.joasvpereira.sessionfeature.domain.data.SessionItem
import pt.joasvpereira.sessionfeature.presentation.create.CreateSessionFeatureScreen
import pt.joasvpereira.sessionfeature.presentation.create.CreateSessionFeatureScreenViewModel
import pt.joasvpereira.sessionfeature.presentation.select.session.SelectSessionFeatureScreen
import pt.joasvpereira.sessionfeature.repository.LocalSessionDataSource

@Composable
fun SessionFeatureEntryPoint(
    onFinished: () -> Unit,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "SelectSessionFeatureScreen") {
        composable(
            "SelectSessionFeatureScreen"
        ) {
            SelectSessionFeatureScreen(getViewModel(), navController = navController) {
                CurrentSession._session = it
                onFinished()
            }
        }
        composable("CreateSessionFeatureScreen?id={id}",
        arguments = listOf(navArgument("id") { defaultValue = -1 })
        ) {
            val id = it.arguments?.getInt("id")
            CreateSessionFeatureScreen(id, getViewModel(), navController = navController)
        }
    }
}