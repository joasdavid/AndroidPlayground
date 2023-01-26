package pt.joasvpereira.xorganizer.presentation.compose

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.ThemeSelector
import kotlinx.coroutines.launch
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.ThemeOption
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.xorganizer.domain.model.DivisionCreationInfo
import pt.joasvpereira.xorganizer.domain.usecase.division.ICreateDivisionsUseCase
import pt.joasvpereira.xorganizer.presentation.icons.DivisionIcon
import pt.joasvpereira.xorganizer.presentation.icons.DivisionIcons

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
                useCase.execute(
                    DivisionCreationInfo(
                        it.title,
                        it.description,
                        it.option.ordinal
                    )
                ).run {
                    navController.popBackStack()
                }
            }
        }
    )
}

data class DivisionIconSelectorState(
    val currentIconPos: Int,
    val direction: DivisionIconSelectorDirection = DivisionIconSelectorDirection.NONE
)

enum class DivisionIconSelectorDirection {
    RIGHT,
    LEFT,
    NONE
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DivisionIconSelector(
    modifier: Modifier = Modifier,
    defaultPos: Int = 0,
    onIconSelected: (DivisionIcon) -> Unit
) {

    var selectedPosition by remember {
        mutableStateOf(DivisionIconSelectorState(currentIconPos = defaultPos))
    }

    val shape = CircleShape
    Row(
        Modifier
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = shape
            )
            .padding(vertical = 10.dp)
            .padding(horizontal = 13.dp)
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    selectedPosition = if (selectedPosition.currentIconPos == 0) {
                        DivisionIconSelectorState(DivisionIcons.all.size - 1, DivisionIconSelectorDirection.LEFT)
                    } else {
                        DivisionIconSelectorState((selectedPosition.currentIconPos - 1), DivisionIconSelectorDirection.LEFT)
                    }
                    onIconSelected(DivisionIcons.all[selectedPosition.currentIconPos])
                }
        )

        Box(
            Modifier
                .background(MaterialTheme.colorScheme.primaryContainer, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = selectedPosition,
                transitionSpec = {
                    when (selectedPosition.direction) {
                        DivisionIconSelectorDirection.RIGHT -> {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.Left,
                                animationSpec = tween(1000)
                            ) + fadeIn() with
                                    slideOutOfContainer(
                                        towards = AnimatedContentScope.SlideDirection.Left,
                                        animationSpec = tween(1000)
                                    ) + fadeOut(animationSpec = tween(1000))
                        }

                        DivisionIconSelectorDirection.LEFT -> {
                            slideIntoContainer(
                                towards = AnimatedContentScope.SlideDirection.Right,
                                animationSpec = tween(1000)
                            ) + fadeIn() with
                                    slideOutOfContainer(
                                        towards = AnimatedContentScope.SlideDirection.Right,
                                        animationSpec = tween(1000)
                                    ) + fadeOut(animationSpec = tween(1000))
                        }

                        DivisionIconSelectorDirection.NONE -> fadeIn() with fadeOut()
                    }
                }
            ) {
                val currentIconData = DivisionIcons.all[it.currentIconPos]
                Icon(
                    painter = painterResource(id = currentIconData.resId),
                    contentDescription = currentIconData.name,
                    modifier = Modifier
                        .padding(15.dp)
                        .size(50.dp),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }

        Icon(
            imageVector = Icons.Default.KeyboardArrowRight,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .clickable {
                    selectedPosition = if (selectedPosition.currentIconPos == DivisionIcons.all.size - 1) {
                        DivisionIconSelectorState(0, DivisionIconSelectorDirection.RIGHT)
                    } else {
                        DivisionIconSelectorState((selectedPosition.currentIconPos + 1), DivisionIconSelectorDirection.RIGHT)
                    }
                    onIconSelected(DivisionIcons.all[selectedPosition.currentIconPos])
                }
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun DivisionIconSelector(
    modifier: Modifier = Modifier,
    defaultIconName: String = "livingRoom",
    onIconSelected: (DivisionIcon) -> Unit
) {
    DivisionIconSelector(
        modifier,
        DivisionIcons.all.indexOf(DivisionIcons.getBy(defaultIconName, DivisionIcons.livingRoom)),
        onIconSelected
    )
}

private object IconResult {
    var divisionIcon: DivisionIcon = DivisionIcons.livingRoom
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
                }
                DivisionIconSelector(modifier = Modifier.fillMaxWidth(), defaultPos = 0) {
                    IconResult.divisionIcon = it
                }
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
                                boxCount = 0,
                                childCount = 0,
                                imageName = IconResult.divisionIcon.name,
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

@Preview
@Composable
fun CreateDivisionScreenPreview() {
    CreateDivisionBody(
        DivisionHolder = DivisionHolder(
            id = 0,
            title = "",
            description = "",
            boxCount = 0,
            childCount = 0,
            option = ThemeOption.THEME_BLUE,
            imageName = ""
        ),
        onClose = {},
        onSave = {}
    )
}