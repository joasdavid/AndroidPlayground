package pt.joasvpereira.xorganizer.compose.common.holder.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Checkbox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.Searchengin
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

@Composable
fun SearchHolder(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    text: String = "Filter",
    icon: ImageVector = LineAwesomeIcons.Searchengin,
    tint: Color = MaterialTheme.colorScheme.primary,
    spaceBetween: Dp = 5.dp,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
) {
    Box(Modifier.clickable { onSearchClick() }) {
        Row(
            modifier = modifier,
            verticalAlignment = verticalAlignment,
            horizontalArrangement = horizontalArrangement
        ) {
            Text(text = text, color = tint)
            SimpleSpace(size = spaceBetween)
            Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(24.dp))
        }
    }
}

@Preview()
@Composable
fun SearchHolderPreview() {
    SearchHolder(
        onSearchClick = { },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(16.dp)
    )
}