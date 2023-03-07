package com.joasvpereira.settings.compose.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
internal fun SettingsSection(
    modifier: Modifier = Modifier,
    sectionName: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = sectionName,
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 17.sp, fontWeight = FontWeight.Bold),
        )
        SimpleSpace(size = 10.dp)
        Column(modifier = Modifier.fillMaxWidth()) {
            content()
        }
        SimpleSpace(size = 2.dp)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.5.dp)
                .background(
                    MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(PERCENT_100),
                ),
        )
    }
}

private const val PERCENT_100 = 100

@UiModePreview
@Composable
private fun SettingsSectionPreview() {
    DynamicTheme {
        Surface(modifier = Modifier) {
            SettingsSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                sectionName = "Session",
            ) {
                SimpleSpace(size = 50.dp)
            }
        }
    }
}
