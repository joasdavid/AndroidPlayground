package pt.joasvpereira.sessionfeature.presentation.select.session

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import pt.joasvpereira.sessionfeature.compose.select.session.SelectSessionScreen

@Composable
fun SelectSessionFeatureScreen(
    viewModel: SelectSessionViewModel,
    navController: NavController? = null
) {
    LaunchedEffect(key1 = null) {
        viewModel.load()
    }
    val state = viewModel.state.value
    if (state.isLoading)
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "LOADING . . .")
        }
    if (!state.isLoading)
        SelectSessionScreen(
            sessionItems = state.sessions,
            onSessionSelected = {},
            onCreateNewSession = { navController?.navigate("CreateSessionFeatureScreen") }
        )
}