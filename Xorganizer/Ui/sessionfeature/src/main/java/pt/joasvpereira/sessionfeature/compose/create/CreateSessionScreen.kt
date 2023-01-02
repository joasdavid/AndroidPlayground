package pt.joasvpereira.sessionfeature.compose.create

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.selector.UploadImagePlaceHolder
import pt.joasvpereira.coreui.text.field.AppTextField

@Composable
internal fun CreateSessionScreen(
    onBackClick : () -> Unit,
    bitmap: Bitmap?,
    onUploadClick: () -> Unit,
    onClearImageClick: () -> Unit,
    sessionName: String,
    onSessionNameChange: (String) -> Unit,
    isButtonEnabled: Boolean,
    onButtonCreateClick : () -> Unit,
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(title = "", onBackClick = onBackClick)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SimpleSpace(size = 60.dp)
                UploadImagePlaceHolder(
                    emptyText = sessionName,
                    bitmap = bitmap,
                    onUploadClick = onUploadClick,
                    onClearImageClick = onClearImageClick
                )
                SimpleSpace(size = 45.dp)
                AppTextField(
                    value = sessionName,
                    onValueChange = onSessionNameChange,
                    placeholder = "Session Name",
                )
            }
            Button(
                onClick = onButtonCreateClick,
                enabled = isButtonEnabled,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 40.dp)
            ) {
                Text(text = "CREATE NEW DIVISION")
            }
        }
    }
}

@Preview
@Composable
private fun CreateSessionScreenPreview() {
    DynamicTheme {
        CreateSessionScreen(
            onBackClick = {},
            onButtonCreateClick = {},
            sessionName = "",
            onSessionNameChange = {},
            bitmap = null,
            onUploadClick = {},
            onClearImageClick = {},
            isButtonEnabled = true
        )
    }
}