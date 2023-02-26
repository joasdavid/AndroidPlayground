package pt.joasvpereira.sessionfeature.presentation.select.session

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pt.joasvpereira.core.domain.data.SessionItem
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.sessionfeature.compose.navigation.navigateToCreateProfile
import pt.joasvpereira.sessionfeature.compose.navigation.navigateToUpdateProfile
import pt.joasvpereira.sessionfeature.compose.select.session.SelectSessionScreen

@Composable
fun SelectSessionFeatureScreen(
    viewModel: SelectSessionViewModel,
    navController: NavController? = null,
    onSessionSelected: (SessionItem) -> Unit,
) {
    LaunchedEffect(key1 = null) {
        viewModel.load()
    }

    val state = viewModel.state.value
    AppScaffold(isLoading = state.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.align(Alignment.TopCenter)) {
                SelectSessionScreen(
                    sessionItems = state.sessions,
                    isEditMode = state.isEditMode,
                    onSessionSelected = {
                        if (state.isEditMode) {
                            navController?.navigateToUpdateProfile(it.id)
                            viewModel.toggleEditMode()
                        } else {
                            onSessionSelected(it)
                        }
                    },
                    onProfileClicked = {
                        navController?.navigateToCreateProfile()
                    },
                )
            }

            if (state.sessions?.isNotEmpty() == true) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 20.dp)
                        .padding(bottom = it.calculateBottomPadding()),
                ) {
                    Button(onClick = { viewModel.toggleEditMode() }) {
                        Text(text = if (state.isEditMode) "Exit edit mode" else "Enter edit mode")
                    }
                }
            }
        }
    }
}
