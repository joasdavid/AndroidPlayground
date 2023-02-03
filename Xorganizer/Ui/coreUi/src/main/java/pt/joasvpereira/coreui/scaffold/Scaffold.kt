@file:OptIn(ExperimentalMaterial3Api::class)

package pt.joasvpereira.coreui.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.R
import pt.joasvpereira.coreui.preview.UiModePreview

data class ToolBarConfig(
    val title: String,
    val leftIcon: ImageVector = Icons.Default.ArrowBack,
    val onLeftIconClick: (() -> Unit)? = null,
    val rightIcon: ImageVector = Icons.Default.Close,
    val onRightIconClick: (() -> Unit)? = null,
    val horizontalPadding: Dp = 0.dp
)

@Composable
fun ToolbarTitleCentered(
    toolBarConfig: ToolBarConfig,
    useInsetsPaddingForStatusBars: Boolean = true
) {
    Box(
        modifier = Modifier
            .then(
                if (useInsetsPaddingForStatusBars) {
                    Modifier.windowInsetsPadding(WindowInsets.statusBars)
                } else {
                    Modifier
                }
            )
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = toolBarConfig.horizontalPadding)
    )
    {
        toolBarConfig.onLeftIconClick?.let { click ->
            Icon(
                imageVector = toolBarConfig.leftIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { click() }
                    .align(Alignment.CenterStart)
            )
        }

        Text(
            text = toolBarConfig.title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )

        toolBarConfig.onRightIconClick?.let { click ->
            Icon(
                imageVector = toolBarConfig.rightIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { click() }
                    .align(Alignment.CenterEnd)
            )
        }
    }
}

@Preview()
@Composable
fun ToolbarTitleCenterdPreview() {
    Column {
        ToolbarTitleCentered(toolBarConfig = ToolBarConfig(title = "Test Toolbar"))
        SimpleSpace(size = 10.dp)
        ToolbarTitleCentered(
            toolBarConfig = ToolBarConfig(
                title = "Test Toolbar with back button",
                onLeftIconClick = {

                }
            )
        )
        SimpleSpace(size = 10.dp)
        ToolbarTitleCentered(
            toolBarConfig = ToolBarConfig(title = "Test Toolbar",horizontalPadding = 20.dp,),
            useInsetsPaddingForStatusBars = false
        )
        SimpleSpace(size = 10.dp)
        Column(
            modifier = Modifier
        ) {
            ToolbarTitleCentered(
                toolBarConfig = ToolBarConfig(title = "divisionName", onLeftIconClick = {},
                    horizontalPadding = 16.dp
                )
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    toolBarConfig: ToolBarConfig? = null,
    isTinted: Boolean = true,
    isLoading: Boolean = false,
    paddingValues: PaddingValues = PaddingValues(horizontal = 20.dp),
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold() {
        val backgroundRes = R.drawable.background
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        if (isTinted) {
            Box(
                modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = .11f))
            )
        }

        Column(Modifier.padding(
            top = it.calculateTopPadding() + paddingValues.calculateTopPadding(),
            start = it.calculateStartPadding(LayoutDirection.Ltr) + paddingValues.calculateStartPadding(LayoutDirection.Ltr),
            end = it.calculateEndPadding(LayoutDirection.Ltr) + paddingValues.calculateEndPadding(LayoutDirection.Ltr),
        )) {
            toolBarConfig?.apply {
                ToolbarTitleCentered(
                    toolBarConfig = this,
                    useInsetsPaddingForStatusBars = false
                )
            }
            content(it)
        }
    }
    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize().clickable {  }.background(MaterialTheme.colorScheme.background.copy(alpha = .75f))) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp).align(Alignment.Center))
        }
    }
}

@UiModePreview
@Composable
fun AppScaffoldPreview() {
    DynamicTheme {
        AppScaffold(
            toolBarConfig = ToolBarConfig(title = "sd"),
            isLoading = false,
            isTinted = true,
            content = { Text(text = "Test") }
        )
    }
}