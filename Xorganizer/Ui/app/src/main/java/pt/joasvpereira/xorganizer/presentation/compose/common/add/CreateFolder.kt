package pt.joasvpereira.xorganizer.presentation.compose.common.add

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import kotlinx.coroutines.launch
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.xorganizer.presentation.compose.common.container.Folder

data class CreateFolderBottomSheetState(
    val isExpanded: Boolean = false,
)

data class CreateFolderContentState(
    val name: String = "",
    val description: String = "",
    val canBeSaved: Boolean = false,
)

class CreateFolderViewModel: ViewModel() {

    var uiState by mutableStateOf(CreateFolderContentState())
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

    suspend fun save(): Boolean  = true
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CreateFolderBottomSheet(
    isExpanded: Boolean,
    onSaveClick: (success: Boolean) -> Unit,
    onCloseClick: () -> Unit,
    viewModel: CreateFolderViewModel = CreateFolderViewModel()
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
                CreateFolderSheetContent(
                    name = viewModel.uiState.name,
                    onNameChange = { viewModel.updateName(it) },
                    description = viewModel.uiState.description,
                    onDescriptionChange = { viewModel.updateDescription(it) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CreateFolderSheetContent(
    name: String,
    onNameChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    isSaveEnabled: Boolean,
    onSaveClick: () -> Unit,
    onCloseClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 25.dp, bottom = 20.dp)
            .padding(horizontal = 16.dp)
            .navigationBarsPadding()
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

        Box(modifier = Modifier.fillMaxWidth()) {
            Folder(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = 75.dp, height = 45.dp)
            ) {
                Text(
                    text = name,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 5.dp)
                )
            }
        }

        SimpleSpace(size = 20.dp)

        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth()
        )
        SimpleSpace(size = 5.dp)
        TextField(
            value = description,
            onValueChange = onDescriptionChange,
            label = { Text(text = "Description") },
            modifier = Modifier.fillMaxWidth()
        )
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
fun CreateFolderBottomSheetPreview() {
    DynamicTheme {
        CreateFolderBottomSheet(onSaveClick = {}, onCloseClick = {}, isExpanded = true)
    }
}