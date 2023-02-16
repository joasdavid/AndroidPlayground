package pt.joasvpereira.xorganizer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.view.WindowCompat
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.xorganizer.presentation.compose.navigation.MainNavigation
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DynamicTheme {
                MainNavigation()
            }
        }
    }
}