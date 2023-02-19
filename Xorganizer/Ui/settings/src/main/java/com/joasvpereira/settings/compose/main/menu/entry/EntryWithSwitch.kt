package com.joasvpereira.settings.compose.main.menu.entry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.coreui.preview.UiModePreview

@Composable
fun EntryWithSwitch(
    modifier: Modifier = Modifier,
    text: String,
    description: String?,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .heightIn(min = 50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onCheckedChange(!checked)
                },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = text)
                Switch(checked = checked, onCheckedChange = onCheckedChange)
            }

            description?.let {
                Text(text = it, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@UiModePreview
@Composable
private fun EntryWithSwitchPreview() {
    DynamicTheme() {
        Surface {
            EntryWithSwitch(
                text = "I'm the text for the EntryWithSwitch",
                description = "This is a optional description to describe the meaning of toggling the switch.",
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Preview(group = "No description")
@Composable
private fun EntryWithSwitchPreview_noDescription() {
    DynamicTheme() {
        Surface {
            EntryWithSwitch(
                text = "I'm the text for the EntryWithSwitch",
                description = null,
                checked = false,
                onCheckedChange = {}
            )
        }
    }
}

@Preview(group = "Switch on")
@Composable
private fun EntryWithSwitchPreview_on() {
    DynamicTheme() {
        Surface {
            EntryWithSwitch(
                text = "I'm the text for the EntryWithSwitch",
                description = "This is a optional description to describe the meaning of toggling the switch.",
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}