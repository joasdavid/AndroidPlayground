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
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.lib.compose.spacer.VerticalSpace
import pt.joasvpereira.coreui.scaffold.AppScaffold
import pt.joasvpereira.coreui.scaffold.ToolBarConfig
import pt.joasvpereira.coreui.selector.UploadImagePlaceHolder
import pt.joasvpereira.coreui.text.field.AppTextField
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
internal fun CreateSessionScreen(
    onBackClick: () -> Unit,
    isLoading: Boolean,
    bitmap: Bitmap?,
    onUploadClick: () -> Unit,
    onClearImageClick: () -> Unit,
    sessionName: String,
    onSessionNameChange: (String) -> Unit,
    isButtonEnabled: Boolean,
    onButtonCreateClick: () -> Unit,
    onButtonDeleteClick: () -> Unit,
    isOnEditMode: Boolean = false,
) {
    AppScaffold(
        toolBarConfig = ToolBarConfig(title = "", onLeftIconClick = onBackClick),
        isLoading = isLoading,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                SimpleSpace(size = 60.dp)

                UploadImagePlaceHolder(
                    emptyText = sessionName,
                    bitmap = bitmap,
                    onUploadClick = onUploadClick,
                    onClearImageClick = onClearImageClick,
                )
                SimpleSpace(size = 45.dp)

                AppTextField(
                    value = sessionName,
                    onValueChange = onSessionNameChange,
                    placeholder = "Session Name",
                )

                VerticalSpace(height = 45.dp)

                ButtonsColumn(
                    modifier = Modifier
                        .fill75PercentWidth()
                        .padding(bottom = 40.dp),
                    onButtonCreateClick = onButtonCreateClick,
                    isButtonEnabled = isButtonEnabled,
                    isOnEditMode = isOnEditMode,
                    onButtonDeleteClick = onButtonDeleteClick,
                )
            }
        }
    }
}

@Suppress("MagicNumber")
fun Modifier.fill75PercentWidth() = then(Modifier.fillMaxWidth(.75f))

@Composable
private fun ButtonsColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    onButtonCreateClick: () -> Unit,
    isButtonEnabled: Boolean,
    isOnEditMode: Boolean,
    onButtonDeleteClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
    ) {
        Button(
            onClick = onButtonCreateClick,
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = if (isOnEditMode) {
                    "Save profile"
                } else {
                    "Create new profile"
                },
            )
        }
        if (isOnEditMode) {
            SimpleSpace(size = 20.dp)
            Button(
                onClick = onButtonDeleteClick,
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "Delete",
                )
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
            isOnEditMode = true,
        )
    }
}
