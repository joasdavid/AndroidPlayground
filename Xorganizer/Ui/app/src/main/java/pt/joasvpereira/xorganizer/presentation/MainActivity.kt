package pt.joasvpereira.xorganizer.presentation

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.xorganizer.presentation.color_scheme.ColorSchemeScreen
import pt.joasvpereira.xorganizer.presentation.compose.CreateDivisionScreen
import pt.joasvpereira.xorganizer.presentation.compose.division.DivisionScreen
import pt.joasvpereira.xorganizer.presentation.compose.division.DivisionScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.folder.FolderScreen
import pt.joasvpereira.xorganizer.presentation.compose.item.ItemScreen
import pt.joasvpereira.xorganizer.presentation.compose.item.ItemScreenViewModel
import pt.joasvpereira.xorganizer.presentation.compose.item.Mode
import pt.joasvpereira.xorganizer.presentation.compose.navigation.ScreenNavigation

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            DynamicTheme {
                /*var tagColumnState by remember { mutableStateOf(TagColumnState(isEditOpen = true,listOfTags = mutableListOf("test", "XPTO", "test3"))) }
                val roundedCornerShape200 = RoundedCornerShape(200.dp)
                TagColumn(
                    tagItemContent = { s: String ->
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .border(
                                    1.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = roundedCornerShape200
                                )
                                .clip(roundedCornerShape200)
                                .padding(5.dp)
                                .clickable { tagColumnState.listOfTags.add("Meh") }
                        ) {
                            Text(
                                style = MaterialTheme.typography.labelSmall,
                                text = s
                            )
                        }
                    },
                    addButton = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                                .border(
                                    1.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = roundedCornerShape200
                                )
                                .clip(roundedCornerShape200)
                                .padding(5.dp)
                                .clickable { tagColumnState.isEditOpen = !tagColumnState.isEditOpen }
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null)
                        }
                    },
                    editorContent = {},
                    tagColumnState = tagColumnState
                )*/
                val navController = rememberNavController()
                Scaffold {
                    it.calculateBottomPadding()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenNavigation.MainScreen.route
                    ) {
                        composable(ScreenNavigation.MainScreen.route) {
                            DynamicTheme {
                                /*MainScreen(
                                    navController = navController,
                                    viewModel = getViewModel()
                                )*/
                            }
                        }
                        composable(
                            ScreenNavigation.DivisionScreen.route,
                            arguments = listOf(navArgument(ScreenNavigation.DivisionScreen.DIVISION_ID) {
                                type = NavType.IntType
                            })
                        ) {
                            val id = it.arguments?.getInt(
                                ScreenNavigation.DivisionScreen.DIVISION_ID,
                                -1
                            ) ?: -1
                            val vm = getViewModel<DivisionScreenViewModel> { parametersOf(id) }
                            DivisionScreen(navController = navController, viewModel = vm)
                        }
                        composable(
                            ScreenNavigation.FolderScreen.route,
                            arguments = listOf(navArgument(ScreenNavigation.FolderScreen.FOLDER_ID) {
                                type = NavType.IntType
                            })
                        ) {
                            FolderScreen(
                                navController = navController,
                                id = it.arguments?.getInt(
                                    ScreenNavigation.FolderScreen.FOLDER_ID,
                                    -1
                                ) ?: -1
                            )
                        }
                        composable(
                            ScreenNavigation.ItemScreen.route,
                            arguments = listOf(navArgument(ScreenNavigation.ItemScreen.ITEM_ID) {
                                type = NavType.IntType
                            })
                        ) {
                            val id =
                                it.arguments?.getInt(ScreenNavigation.ItemScreen.ITEM_ID, -1) ?: -1
                            val vm = getViewModel<ItemScreenViewModel> {
                                parametersOf(
                                    id,
                                    Mode.READ
                                )
                            }
                            ItemScreen(
                                viewModel = vm,
                                navController = navController
                            )
                        }
                        composable(ScreenNavigation.CreateDivisionScreen.route) {
                            CreateDivisionScreen(
                                navController = navController,
                                useCase = get()
                            )
                        }
                        composable(ScreenNavigation.TestColorDynamicScreen.route) {
                            DynamicTheme { ColorSchemeScreen() }
                        }
                        composable(ScreenNavigation.TestColorBlueScreen.route) {
                            DynamicTheme(ThemeOption.THEME_BLUE) { ColorSchemeScreen() }
                        }
                        composable(ScreenNavigation.TestColorGreenScreen.route) {
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