@file:OptIn(ExperimentalMaterial3Api::class)

package pt.joasvpereira.coreui.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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

data class ToolBarConfig(
    val title: String,
    val backIcon: ImageVector = Icons.Default.ArrowBack,
    val onBackClick: (() -> Unit)? = null
)

@Composable
fun ToolbarTitleCentered(
    toolBarConfig: ToolBarConfig,
    horizontalPadding: Dp = 16.dp,
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
            .padding(horizontal = horizontalPadding)
    )
    {
        toolBarConfig.onBackClick?.let { backClick ->
            Icon(
                imageVector = toolBarConfig.backIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .clickable { backClick() }
                    .align(Alignment.CenterStart)
            )
        }

        Text(
            text = toolBarConfig.title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center)
        )

        // TODO: this will be implemented later
        /*Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null
        )*/
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
                onBackClick = {

                }
            )
        )
        SimpleSpace(size = 10.dp)
        ToolbarTitleCentered(
            toolBarConfig = ToolBarConfig(title = "Test Toolbar"),
            horizontalPadding = 20.dp,
            useInsetsPaddingForStatusBars = false
        )
        SimpleSpace(size = 10.dp)
        Column(
            modifier = Modifier
        ) {
            ToolbarTitleCentered(
                toolBarConfig = ToolBarConfig(title = "divisionName", onBackClick = {}),
                horizontalPadding = 16.dp
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    toolBarConfig: ToolBarConfig? = null,
    content: @Composable () -> Unit
) {
    Scaffold() {
        val backgroundRes = R.drawable.background
        Image(
            painter = painterResource(id = backgroundRes),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Column(Modifier.padding(
            top = it.calculateTopPadding(),
            start = it.calculateStartPadding(LayoutDirection.Ltr) + 20.dp,
            end = it.calculateEndPadding(LayoutDirection.Ltr) + 20.dp,
            bottom = it.calculateBottomPadding()
        )) {
            toolBarConfig?.apply {
                ToolbarTitleCentered(
                    toolBarConfig = this,
                    horizontalPadding = 0.dp,
                    useInsetsPaddingForStatusBars = false
                )
            }
            content()
        }
    }
}

@Preview
@Composable
fun AppScaffoldPreview() {
    DynamicTheme {
        AppScaffold(
            toolBarConfig = ToolBarConfig(title = "sd"),
            content = { Text(text = "Test") }
        )
    }
}