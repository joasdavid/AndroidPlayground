package pt.joasvpereira.sessionfeature.presentation.create

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import pt.joasvpereira.sessionfeature.compose.create.CreateSessionScreen

@Composable
fun CreateSessionFeatureScreen(viewModel: CreateSessionFeatureScreenViewModel) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { viewModel.processContentResult(context, it) }
    )
    CreateSessionScreen(
        onBackClick = {  },
        isLoading = viewModel.state.isLoading,
        bitmap = viewModel.state.bitmap,
        onUploadClick = { launcher.launch("image/*") },
        onClearImageClick = { viewModel.removeImage() },
        sessionName = viewModel.state.sessionName,
        onSessionNameChange = { viewModel.changeName(it) },
        isButtonEnabled = viewModel.state.isButtonEnabled,
        onButtonCreateClick = { viewModel.save() }
    )
}