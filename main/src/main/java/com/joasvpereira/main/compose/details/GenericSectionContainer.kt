package com.joasvpereira.main.compose.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun GenericSectionContainer(
    modifier: Modifier = Modifier,
    headerText: String,
    headerStartPadding: Dp = 10.dp,
    headerEndPadding: Dp = 10.dp,
    clickableIcon: HeaderClickableIcon? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Row(
            modifier = Modifier
                .padding(start = headerStartPadding)
                .padding(end = headerEndPadding),
        ) {
            Text(
                text = headerText,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f),
            )
            clickableIcon?.run {
                Icon(
                    painter = iconPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(24.dp)
                        .padding(2.dp)
                        .clip(CircleShape)
                        .clickable { onClick() },
                )
            }
        }
        SimpleSpace(size = 5.dp)
        Card(
            Modifier
                .fillMaxWidth(),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 10.dp),
        ) {
            content()
        }
    }
}

data class HeaderClickableIcon(
    val iconPainter: Painter,
    val onClick: () -> Unit,
)

@Preview
@Composable
private fun GenericSectionContainerPreview() {
    DynamicTheme(ThemeOption.THEME_BLUE) {
        GenericSectionContainer(
            modifier = Modifier,
            headerText = "Header",
            clickableIcon = HeaderClickableIcon(
                iconPainter = rememberVectorPainter(image = Icons.Default.Edit),
                onClick = {},
            ),
            content = { SimpleSpace(size = 100.dp) },
        )
    }
}
