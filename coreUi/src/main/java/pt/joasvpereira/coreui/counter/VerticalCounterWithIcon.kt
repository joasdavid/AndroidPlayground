package pt.joasvpereira.coreui.counter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
fun VerticalCounterWithIcon(
    color: Color = LocalContentColor.current,
    iconData: IconData,
    count: Int = 0,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = iconData.painter,
            contentDescription = iconData.contentDescription,
            tint = color,
            modifier = Modifier.size(16.dp),
        )
        Text(text = "$count", color = color)
    }
}

@Preview
@Composable
private fun VerticalCounterWithIconPreview() {
    DynamicTheme() {
        VerticalCounterWithIcon(
            color = MaterialTheme.colorScheme.primary,
            iconData = IconData(
                painter = rememberVectorPainter(image = Icons.Default.Security),
                contentDescription = "",
            ),
            count = 22,
        )
    }
}
