package pt.joasvpereira.core.navigation

import androidx.navigation.NavController

fun NavController.navigateAndResetStack(route : String) {
    navigate(route){
        currentBackStackEntry?.destination?.route?.run {
            popUpTo(this) {
                inclusive =  true
            }
            launchSingleTop = true
        }
    }
}