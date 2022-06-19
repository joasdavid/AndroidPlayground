package pt.joasvpereira.xorganizer.presentation.compose.common.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import kotlinx.coroutines.launch
import pt.joasvpereira.xorganizer.presentation.compose.common.shield.NameShield
import pt.joasvpereira.xorganizer.presentation.theme.DynamicTheme

data class CreateItemBottomSheetState(
    val isExpanded: Boolean = false,
)

data class CreateItemContentState(
    val name: String = "",
    val description: String = "",
    val isStatusUsed: Boolean = false,
    val tags: List<String> = emptyList(),
    val canBeSaved: Boolean = false,
)

class CreateItemViewModel : ViewModel() {

    var uiState by mutableStateOf(CreateItemContentState())
        private set

    fun updateName(name: String) {
        uiState = uiState.copy(
            name = name,
            canBeSaved = name.isNotBlank()
        )
    }

    fun updateDescription(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun onStateChange(value: Boolean) {
        uiState = uiState.copy(isStatusUsed = value)
    }

    suspend fun save(): Boolean = true
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateItemBottomSheet(
    isExpanded: Boolean,
    onSaveClick: (success: Boolean) -> Unit,
    onCloseClick: () -> Unit,
    viewModel: CreateItemViewModel = CreateItemViewModel()
) {
    val coroutineScope = rememberCoroutineScope()
    AnimatedVisibility(
        visible = isExpanded,
        enter = slideInVertically(
            initialOffsetY = { it * 2 },
            animationSpec = tween(2000)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it * 2 },
            animationSpec = tween(2000)
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Sheet {
                CreateItemSheetContent(
                    name = viewModel.uiState.name,
                    onNameChange = { viewModel.updateName(it) },
                    description = viewModel.uiState.description,
                    onDescriptionChange = { viewModel.updateDescription(it) },
                    state = viewModel.uiState.isStatusUsed,
                    onStateChange = { viewModel.onStateChange(it) },
                    isSaveEnabled = viewModel.uiState.canBeSaved,
                    onSaveClick = {
                        coroutineScope.launch {
                            onSaveClick(viewModel.save())
                        }
                    },
                    onCloseClick = onCloseClick,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BoxScope.Sheet(content: @Composable () -> Unit) {
    Card(
        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
        modifier = Modifier.align(Alignment.BottomCenter),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 20.dp)
    ) {
        content()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun CreateItemSheetContent(
    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    state: Boolean,
    onStateChange: (Boolean) -> Unit,
    isSaveEnabled: Boolean,
    onSaveClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, bottom = 20.dp)
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Creating new folder.",
                modifier = Modifier.align(Alignment.Center)
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .clip(CircleShape)
                    .clickable { onCloseClick() })
        }


        SimpleSpace(size = 20.dp)

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            NameShield(text = name, modifier = Modifier.size(75.dp))
        }

        SimpleSpace(size = 20.dp)

        val (focusRequester) = FocusRequester.createRefs()
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(
                onNext = { focusRequester.requestFocus() }
            )
        )
        SimpleSpace(size = 5.dp)
        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text(text = "Description") },
            modifier = Modifier.fillMaxWidth().focusRequester(focusRequester),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            )
        )
        SimpleSpace(size = 5.dp)
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(checked = state, onCheckedChange = onStateChange)
            SimpleSpace(size = 5.dp)
            AnimatedVisibility(visible = state) {
                Text(text = "Used")
            }
            AnimatedVisibility(visible = !state) {
                Text(text = "Unused")
            }
        }
        SimpleSpace(size = 20.dp)
        Button(
            onClick = onSaveClick,
            enabled = isSaveEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Save")
        }
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xfff
)
@Composable
fun CreateItemBottomSheetPreview() {
    DynamicTheme {
        CreateItemBottomSheet(onSaveClick = {}, onCloseClick = {}, isExpanded = true)
    }
}