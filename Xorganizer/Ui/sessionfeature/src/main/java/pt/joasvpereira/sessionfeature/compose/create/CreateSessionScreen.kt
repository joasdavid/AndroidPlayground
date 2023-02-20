package pt.joasvpereira.sessionfeature.compose.create

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.selector.UploadImagePlaceHolder
import pt.joasvpereira.coreui.text.field.AppTextField

@Composable
internal fun CreateSessionScreen(
    onBackClick : () -> Unit,
    isLoading: Boolean,
    bitmap: Bitmap?,
    onUploadClick: () -> Unit,
    onClearImageClick: () -> Unit,
    sessionName: String,
    onSessionNameChange: (String) -> Unit,
    isButtonEnabled: Boolean,
    onButtonCreateClick : () -> Unit,
    onButtonDeleteClick : () -> Unit,
    isOnEditMode: Boolean = false
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(title = "", onLeftIconClick = onBackClick),
        isLoading = isLoading
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
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = onButtonCreateClick,
                    enabled = isButtonEnabled,
                ) {
                    Text(
                        text = if (isOnEditMode) {
                            "Save profile"
                        }  else {
                            "Create new profile"
                        }
                    )
                }
                if (isOnEditMode) {
                    SimpleSpace(size = 20.dp)
                    Button(
                        onClick = onButtonDeleteClick,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text(
                            text = "Delete",
                        )
                    }
                }
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
            isButtonEnabled = true,
            isLoading = false,
            onButtonDeleteClick = {},
            isOnEditMode = true
        )
    }
}