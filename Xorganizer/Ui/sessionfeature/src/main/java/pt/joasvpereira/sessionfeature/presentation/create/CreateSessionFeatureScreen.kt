package pt.joasvpereira.sessionfeature.presentation.create

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import pt.joasvpereira.sessionfeature.compose.create.CreateSessionScreen

@Composable
fun CreateSessionFeatureScreen(
    id: Int?,
    viewModel: CreateSessionFeatureScreenViewModel,
    navController: NavController? = null
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { viewModel.processContentResult(context, it) }
    )

    LaunchedEffect(key1 = viewModel.state.saveState.toString()) {
        if (viewModel.state.saveState is SaveState.Success) navController?.popBackStack()
    }

    LaunchedEffect(key1 = null) {
        id?.let {
            viewModel.load(id)
        }
    }

    CreateSessionScreen(
        onBackClick = { navController?.popBackStack()  },
        isLoading = viewModel.state.isLoading,
        bitmap = viewModel.state.bitmap,
        onUploadClick = { launcher.launch("image/*") },
        onClearImageClick = { viewModel.removeImage() },
        sessionName = viewModel.state.sessionName,
        onSessionNameChange = { viewModel.changeName(it) },
        isButtonEnabled = viewModel.state.isButtonEnabled,
        onButtonCreateClick = { viewModel.save() },
        isOnEditMode = viewModel.state.mode is Mode.Edit
    )
}