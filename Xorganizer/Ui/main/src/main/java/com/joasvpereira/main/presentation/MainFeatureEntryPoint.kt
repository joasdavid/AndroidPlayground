package com.joasvpereira.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joasvpereira.main.presentation.box.BoxScreen
import com.joasvpereira.main.presentation.create.CreateDivisionScreen
import com.joasvpereira.main.presentation.dashboard.DashboardFeatureScreen
import com.joasvpereira.main.presentation.details.ItemDetailScreen
import com.joasvpereira.main.presentation.division.DivisionsFeatureScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MainFeatureEntryPoint(
    onSwitchProfile: () -> Unit
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "DashboardFeatureScreen") {
        composable(
            "DashboardFeatureScreen"
        ) {
            DashboardFeatureScreen(viewModel = getViewModel(), navController = navController, onSwitchProfile = onSwitchProfile)
        }
        composable(
            "CreateDivisionsFeatureScreen?id={id}",
            arguments = listOf(navArgument("id") { defaultValue = -1 })
        ) {
            val id = it.arguments?.getInt("id").takeIf { it != -1 }
            CreateDivisionScreen(divisionId = id, getViewModel(), navController = navController)
        }

        composable(
            "DivisionFeatureScreen?id={id}",
            arguments = listOf(navArgument("id", builder = {type = NavType.IntType
                defaultValue = -1
            }))
        ) {
            val id = it.arguments?.getInt("id") ?: -1
            DivisionsFeatureScreen(getViewModel() { parametersOf(id) }, navController = navController)
        }

        composable(
            "ItemDetailScreen?id={id}",
            arguments = listOf(navArgument("id", builder = {type = NavType.IntType
                defaultValue = -1
            }))
        ) {
            val id = it.arguments?.getInt("id") ?: -1
            ItemDetailScreen(getViewModel() { parametersOf(id) }, navController = navController)
        }

        composable(
            "BoxScreen?id={id}",
            arguments = listOf(navArgument("id", builder = {type = NavType.IntType
                defaultValue = -1
            }))
        ) {
            val id = it.arguments?.getInt("id") ?: -1
            BoxScreen(getViewModel() { parametersOf(id) }, navController = navController)
        }
    }
}