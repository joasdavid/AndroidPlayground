package pt.joasvpereira.sessionfeature.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import pt.joasvpereira.core.repository.local.Db
import pt.joasvpereira.sessionfeature.presentation.create.CreateSessionFeatureScreen
import pt.joasvpereira.sessionfeature.presentation.create.CreateSessionFeatureScreenViewModel
import pt.joasvpereira.sessionfeature.presentation.select.session.SelectSessionFeatureScreen
import pt.joasvpereira.sessionfeature.repository.LocalSessionDataSource

@Composable
fun SessionFeatureEntryPoint() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "SelectSessionFeatureScreen") {
        composable("SelectSessionFeatureScreen") {
            SelectSessionFeatureScreen(getViewModel(), navController = navController)
        }
        composable("CreateSessionFeatureScreen") {
            val vm = CreateSessionFeatureScreenViewModel(LocalSessionDataSource(get<Db>().sessionDao()))
            CreateSessionFeatureScreen(vm, navController = navController)
        }
    }
}