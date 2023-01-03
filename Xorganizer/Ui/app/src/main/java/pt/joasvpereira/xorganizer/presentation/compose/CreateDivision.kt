package pt.joasvpereira.xorganizer.presentation.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.IconSelector
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeSelector
import compose.icons.AllIcons
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.HomeSolid
import kotlinx.coroutines.launch
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.xorganizer.R
import pt.joasvpereira.xorganizer.domain.model.DivisionCreationInfo
import pt.joasvpereira.xorganizer.domain.usecase.division.ICreateDivisionsUseCase

@Composable
fun CreateDivisionScreen(navController: NavController, useCase: ICreateDivisionsUseCase) {
    val scope = rememberCoroutineScope()
    CreateDivisionBody(
        DivisionHolder = null,
        onClose = {
            navController.popBackStack()
        },
        onSave = {
            scope.launch {
                useCase.execute(DivisionCreationInfo(
                    it.title,
                    it.description,
                    it.option.ordinal
                )).run {
                    navController.popBackStack()
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateDivisionBody(
    DivisionHolder: DivisionHolder? = null,
    onClose: () -> Unit = {},
    onSave: (DivisionHolder) -> Unit = {}
) {
    var selectedThemeOption: ThemeOption by remember {
        mutableStateOf(DivisionHolder?.option ?: ThemeOption.THEME_DEFAULT)
    }
    var divisionNameText by remember { mutableStateOf(DivisionHolder?.title ?: "") }
    var descriptionText by remember { mutableStateOf(DivisionHolder?.description ?: "") }
    DynamicTheme(selectedThemeOption) {
        AppScaffold {
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
                        mutableStateOf(DivisionHolder?.vectorImg ?: LineAwesomeIcons.HomeSolid)
                    }
                    IconSelector(
                        iconSelected = iconSelected,
                        onIconSelected = { iconSelected = it },
                        iconOptions = LineAwesomeIcons.AllIcons.take(35)
                    )

                    Spacer(modifier = Modifier.size(20.dp))

                    AppTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = divisionNameText,
                        onValueChange = { divisionNameText = it },
                        placeholder = "Division name",
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    AppTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = descriptionText,
                        onValueChange = { descriptionText = it },
                        placeholder = "description",
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
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
                                DivisionHolder(
                                    id = 1,
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