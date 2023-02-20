package com.joasvpereira.settings.compose.main.theme

import android.os.Build
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.settings.compose.main.SettingsSection
import com.joasvpereira.settings.compose.main.menu.entry.EntryWithSelectableOption
import com.joasvpereira.settings.compose.main.menu.entry.EntryWithSwitch
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.preview.UiModePreview

@Composable
internal fun ThemeSettingsSection(
    modifier: Modifier = Modifier,
    hasMaterialYou : Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    isMaterialYouEnabled : Boolean,
    onMaterialYouSwitchChange : (Boolean) -> Unit,
    themeModeSelectedOption : Int,
    onThemeModeChange : (Int) -> Unit
) {
    SettingsSection(
        modifier = modifier,
        sectionName = "Theme"
    ) {
        if (hasMaterialYou) {
            EntryWithSwitch(
                text = "Use Material you",
                description = "When turn on will use the system colors as your default theme instead of the app default theme.",
                checked = isMaterialYouEnabled,
                onCheckedChange = { onMaterialYouSwitchChange(it) }
            )

            SimpleSpace(size = 20.dp)
        }

        EntryWithSelectableOption(
            text = "Theme mode",
            listOfOptions = listOf("Default", "Light", "Dark"),
            description = """
                You can choose 3 different options:                 
                  - Default, that will use the system light or dark mode;
                  - Light, will force the app to be on Light mode;
                  - Dark, will force the app to be on Dark mode;
            """.trimIndent(),
            selectedOption = themeModeSelectedOption,
            onOptionChanged = onThemeModeChange
        )

        SimpleSpace(size = 20.dp)
    }
}

@UiModePreview
@Composable
private fun ThemeSettingsSectionPreview() {
    DynamicTheme {
        Surface(modifier = Modifier) {
            ThemeSettingsSection(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 20.dp)
                    .fillMaxWidth(),
                hasMaterialYou = true,
                isMaterialYouEnabled = false,
                onMaterialYouSwitchChange = {},
                themeModeSelectedOption = 2,
                onThemeModeChange = {},
                )
        }
    }
}