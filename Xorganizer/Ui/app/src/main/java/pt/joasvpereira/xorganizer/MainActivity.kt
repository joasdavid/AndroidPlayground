package pt.joasvpereira.xorganizer

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.CreateDivisionScreen
import pt.joasvpereira.xorganizer.compose.MainScreen
import pt.joasvpereira.xorganizer.test.color_scheme.ColorSchemeScreen
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.JetchatTheme
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                MainContainer()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
private fun MainContainer() {
    DynamicTheme(option = ThemeOption.THEME_BLUE) {
        Column {
            var pos by remember {
                mutableStateOf(3)
            }
            Row {
                Button(onClick = { pos = 0 }) {
                    Text(text = "Dynamic")
                }
                Button(onClick = { pos = 1 }) {
                    Text(text = "Blue")
                }
                Button(onClick = { pos = 3 }) {
                    Text(text = "main")
                }
                Button(onClick = { pos = 2 }) {
                    Text(text = "Create")
                }
            }
            when (pos) {
                0 -> JetchatTheme {
                    ColorSchemeScreen()
                }
                1 -> DynamicTheme(ThemeOption.THEME_BLUE) {
                    ColorSchemeScreen()
                }
                2 -> DynamicTheme(ThemeOption.THEME_BLUE) {
                    CreateDivisionScreen()
                }
                3 -> MainScreen()
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
    DynamicTheme(ThemeOption.THEME_BLUE) { MainContainer() }
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DarkPreview() {
    DynamicTheme(ThemeOption.THEME_BLUE) { MainContainer() }
}