package pt.joasvpereira.core.navigation

import androidx.navigation.NavController
import com.joasvpereira.loggger.extentions.logThis

fun NavController.navigateAndResetStack(route: String) {
    navigate(route) {
        popUpTo(0) {
            inclusive = true
        }
        launchSingleTop = true
    }
}