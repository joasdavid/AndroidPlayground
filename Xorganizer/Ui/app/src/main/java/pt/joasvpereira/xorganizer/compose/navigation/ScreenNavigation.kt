package pt.joasvpereira.xorganizer.compose.navigation

import androidx.navigation.NavGraphBuilder

sealed class ScreenNavigation(val route: String) {
    object MainScreen: ScreenNavigation(route = "main_screen")
    object CreateDivisionScreen: ScreenNavigation(route = "create_screen")
    object TestColorDynamicScreen: ScreenNavigation(route = "color_screen_dynamic")
    object TestColorBlueScreen: ScreenNavigation(route = "color_screen_blue")
    object TestColorGreenScreen: ScreenNavigation(route = "color_screen_green")
}