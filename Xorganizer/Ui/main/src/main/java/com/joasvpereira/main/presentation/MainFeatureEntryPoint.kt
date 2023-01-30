package com.joasvpereira.main.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.joasvpereira.main.presentation.create.CreateDivisionScreen
import com.joasvpereira.main.presentation.dashboard.DashboardFeatureScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun MainFeatureEntryPoint() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "DashboardFeatureScreen") {
        composable(
            "DashboardFeatureScreen"
        ) {
            DashboardFeatureScreen(viewModel = getViewModel(), navController = navController)
        }
            composable("CreateDivisionsFeatureScreen?id={id}",
            arguments = listOf(navArgument("id") { defaultValue = -1 })
        ) {
            val id = it.arguments?.getInt("id").takeIf { it != -1 }
            CreateDivisionScreen(divisionId = id, getViewModel(), navController = navController)
        }
    }
}