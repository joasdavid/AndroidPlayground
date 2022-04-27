package pt.joasvpereira.xorganizer.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.IconSelector
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeSelector
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.HomeSolid
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.SystemUiOptions
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

@Composable
fun CreateDivisionScreen(navController: NavController) {
    CreateDivisionBody(
        itemTest = null,
        onClose = {
            navController.popBackStack()
        },
        onSave = {
            navController.popBackStack()
        }
    )
}

@Composable
fun CreateDivisionBody(
    itemTest: itemTest? = null,
    onClose: () -> Unit = {},
    onSave: (itemTest) -> Unit = {}
) {
    var selectedThemeOption: ThemeOption by remember {
        mutableStateOf(itemTest?.option ?: ThemeOption.THEME_DEFAULT)
    }
    var divisionNameText by remember { mutableStateOf(itemTest?.title ?: "") }
    var descriptionText by remember { mutableStateOf(itemTest?.description ?: "") }
    DynamicTheme(selectedThemeOption, systemUiOptions = SystemUiOptions.OverrideSystemColor) {
        Surface(color = MaterialTheme.colorScheme.primaryContainer) {
            Box(Modifier.fillMaxSize()) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(CircleShape)
                        .clickable { onClose() }
                        .padding(24.dp)
                        .size(24.dp),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Screen."
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val focusManager = LocalFocusManager.current

                    Spacer(modifier = Modifier.size(100.dp))

                    var iconSelected by remember {
                        mutableStateOf(itemTest?.vectorImg ?: LineAwesomeIcons.HomeSolid)
                    }
                    IconSelector(
                        iconSelected = iconSelected,
                        onIconSelected = { iconSelected = it }
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = divisionNameText,
                        onValueChange = { divisionNameText = it },
                        label = { Text("Division name") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        })
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = descriptionText,
                        onValueChange = { descriptionText = it },
                        label = { Text("description") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.clearFocus()
                        })
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    ThemeSelector {
                        selectedThemeOption = it
                    }
                    SimpleSpace(size = 45.dp)
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            onSave(
                                itemTest(
                                    title = divisionNameText,
                                    description = descriptionText,
                                    vectorImg = iconSelected,
                                    boxCount = 0,
                                    childCount = 0,
                                    option = selectedThemeOption
                                )
                            )
                        }
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateDivisionScreenPreview() {
    CreateDivisionBody()
}