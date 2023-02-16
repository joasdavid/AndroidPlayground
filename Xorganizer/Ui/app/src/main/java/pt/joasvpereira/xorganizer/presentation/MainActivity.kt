package pt.joasvpereira.xorganizer.presentation

import android.os.Bundle
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.loggger.extentions.logThis
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.sessionfeature.domain.usecase.ILoadSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.ISessionsUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.LoadSessionUseCase
import pt.joasvpereira.sessionfeature.domain.usecase.SessionsUseCase
import pt.joasvpereira.xorganizer.presentation.compose.navigation.MainNavigation
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    val viewModel by viewModel<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition { viewModel.isLoading }
        setContent {
            DynamicTheme {
                if (!viewModel.isLoading)
                MainNavigation()
            }
        }
    }
}

class MainViewModel(sessionsUseCase: ILoadSessionUseCase): ViewModel() {

    private var _isLoading by mutableStateOf(true)
    val isLoading
    get() = _isLoading
    init {
        viewModelScope.launch {
            sessionsUseCase.execute(EmptyParams()).logThis(tag = "JVP") { "Load result = $it" }
            _isLoading = false
        }
    }
}