package pt.joasvpereira.xorganizer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import pt.joasvpereira.xorganizer.compose.CreateDivisionScreen
import pt.joasvpereira.xorganizer.compose.MainScreen
import pt.joasvpereira.xorganizer.compose.navigation.ScreenNavigation
import pt.joasvpereira.xorganizer.test.color_scheme.ColorSchemeScreen
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.SystemUiOptions
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicTheme {
                val navController = rememberAnimatedNavController()
                Scaffold {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = ScreenNavigation.MainScreen.route
                    ) {
                        composable(
                            ScreenNavigation.MainScreen.route,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            },
                        ) {
                            DynamicTheme(systemUiOptions = SystemUiOptions.SetSystemColor) {
                                MainScreen(navController)
                            }
                        }
                        composable(
                            ScreenNavigation.CreateDivisionScreen.route,
                            enterTransition = {
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Left,
                                    animationSpec = tween(700)
                                )
                            },
                            popEnterTransition = {
                                slideIntoContainer(
                                    AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Right,
                                    animationSpec = tween(700)
                                )
                            },
                        ) {
                            CreateDivisionScreen(navController)
                        }
                        composable(
                            ScreenNavigation.TestColorDynamicScreen.route,
                            enterTransition = { null },
                            exitTransition = { null },
                            popEnterTransition = { null },
                            popExitTransition = { null },
                        ) {
                            DynamicTheme { ColorSchemeScreen() }
                        }
                        composable(
                            ScreenNavigation.TestColorBlueScreen.route,
                            enterTransition = { null },
                            exitTransition = { null },
                            popEnterTransition = { null },
                            popExitTransition = { null },
                        ) {
                            DynamicTheme(ThemeOption.THEME_BLUE) { ColorSchemeScreen() }
                        }
                        composable(
                            ScreenNavigation.TestColorGreenScreen.route,
                            enterTransition = { null },
                            exitTransition = { null },
                            popEnterTransition = { null },
                            popExitTransition = { null },
                        ) {
                            DynamicTheme(ThemeOption.THEME_GREEN) { ColorSchemeScreen() }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
private fun MainContainer() {
    DynamicTheme {
        Box {
            var pos by remember { mutableStateOf(3) }
            when (pos) {
                0 -> DynamicTheme {
                    ColorSchemeScreen()
                }
                1 -> DynamicTheme(ThemeOption.THEME_BLUE) {
                    ColorSchemeScreen()
                }
                2 -> DynamicTheme(ThemeOption.THEME_GREEN) {
                    ColorSchemeScreen()
                }
                //3 -> DynamicTheme(systemUiOptions = SystemUiOptions.SetSystemColor) { MainScreenBody() }
            }
            Row(Modifier.align(Alignment.BottomCenter)) {
                Button(onClick = { pos = 0 }) {
                    Text(text = "Default")
                }
                Button(onClick = { pos = 1 }) {
                    Text(text = "Blue")
                }
                Button(onClick = { pos = 2 }) {
                    Text(text = "Green")
                }
                Button(onClick = { pos = 3 }) {
                    Text(text = "Main")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DynamicTheme {
        Surface {
            MainContainer()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    DynamicTheme {
        Surface {
            MainContainer()
        }
    }
}