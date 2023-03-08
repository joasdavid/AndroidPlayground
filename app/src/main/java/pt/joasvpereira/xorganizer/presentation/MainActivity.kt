package pt.joasvpereira.xorganizer.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joasvpereira.loggger.extentions.logThis
import com.joasvpereira.sessioncore.domail.usecases.ILoadSessionUseCase
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import pt.joasvpereira.core.domain.usecase.EmptyParams
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.core.settings.repository.ThemePreferencesDataSource
import pt.joasvpereira.coreui.screen.LoadWindowSize
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.LocalThemeConfig
import pt.joasvpereira.xorganizer.presentation.compose.navigation.MainNavigation

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    val viewModel by viewModel<MainViewModel>()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val splash = installSplashScreen()
        splash.setKeepOnScreenCondition { viewModel.isLoading }
        setContent {
            LoadWindowSize(this)
            val theme =
                get<ThemePreferencesDataSource>().getUserFromPreferencesStore().collectAsState(
                    initial = ThemePreference(
                        isMaterialYouEnabled = false,
                        mode = ThemePreference.ThemeMode.DEFAULT,
                    ),
                )
            theme.value.logThis(tag = "themeTest") {
                "Value provided to LocalThemeConfig is $it"
            }
            CompositionLocalProvider(LocalThemeConfig provides theme.value) {
                DynamicTheme {
                    if (!viewModel.isLoading) {
                        MainNavigation()
                    }
                }
            }
        }
    }
}

class MainViewModel(sessionsUseCase: ILoadSessionUseCase) : ViewModel() {

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
/**
2023-03-07 19:22:44.244  6521-6521  ScreenSize              pt.joasvpereira.xorganizer           D  411.0.dp
2023-03-07 19:22:44.244  6521-6521  ScreenSize              pt.joasvpereira.xorganizer           D  854.0.dp
 */
