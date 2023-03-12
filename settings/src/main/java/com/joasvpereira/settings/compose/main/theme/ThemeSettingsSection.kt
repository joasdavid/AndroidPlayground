package com.joasvpereira.settings.compose.main.theme

import android.os.Build
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import com.joasvpereira.settings.R
import com.joasvpereira.settings.compose.main.SettingsSection
import com.joasvpereira.settings.compose.main.menu.entry.EntryWithSelectableOption
import com.joasvpereira.settings.compose.main.menu.entry.EntryWithSwitch
import com.joasvpereira.settings.data.ThemeModeMapper
import pt.joasvpereira.core.settings.domain.data.ThemePreference
import pt.joasvpereira.coreui.preview.UiModePreview
import pt.joasvpereira.coreui.theme.DynamicTheme

@Composable
internal fun ThemeSettingsSection(
    modifier: Modifier = Modifier,
    hasMaterialYou: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    isMaterialYouEnabled: Boolean,
    onMaterialYouSwitchChange: (Boolean) -> Unit,
    themeModeSelectedOption: ThemePreference.ThemeMode,
    onThemeModeChange: (ThemePreference.ThemeMode) -> Unit,
) {
    SettingsSection(
        modifier = modifier,
        sectionName = stringResource(R.string.theme_section_title),
    ) {
        if (hasMaterialYou) {
            EntryWithSwitch(
                text = stringResource(R.string.label_material_you),
                description = stringResource(R.string.description_material_you),
                checked = isMaterialYouEnabled,
                onCheckedChange = { onMaterialYouSwitchChange(it) },
            )

            SimpleSpace(size = 20.dp)
        }

        val context = LocalContext.current
        val allModes = ThemePreference.ThemeMode.values().asList()
        val selectedOption = allModes.first { it == themeModeSelectedOption }
        val selectedOptionLabel = ThemeModeMapper.mapToString(context, selectedOption)
        val list = allModes.map {
            ThemeModeMapper.mapToString(context, it)
        }

        EntryWithSelectableOption(
            text = stringResource(R.string.label_theme_mode),
            listOfOptions = list,
            description = stringResource(R.string.description_theme_mode).trimIndent(),
            selectedOption = list.indexOf(selectedOptionLabel),
            onOptionChanged = { index ->
                onThemeModeChange(ThemeModeMapper.mapFromString(context, list[index]))
            },
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
                themeModeSelectedOption = ThemePreference.ThemeMode.DEFAULT,
                onThemeModeChange = {},
            )
        }
    }
}

@Preview(locale = "pt")
@Composable
private fun ThemeSettingsSectionPreviewPT() {
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
                themeModeSelectedOption = ThemePreference.ThemeMode.DEFAULT,
                onThemeModeChange = {},
            )
        }
    }
}
