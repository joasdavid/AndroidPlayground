package com.joasvpereira.dev.mokeupui.compose.screen.organizer.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.joasvpereira.xorganizer.ui.theme.DynamicTheme
import pt.joasvpereira.xorganizer.ui.theme.ThemeOption

@Composable
fun CreateDivisionScreen() {
    var selectedThemeOption: ThemeOption by remember { mutableStateOf(ThemeOption.THEME_DEFAULT) }
    var divisionNameText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }
    DynamicTheme(selectedThemeOption) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.primaryContainer),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.size(100.dp))

                IconSelector()

                Spacer(modifier = Modifier.size(20.dp))

                OutlinedTextField(
                    value = divisionNameText,
                    onValueChange = { divisionNameText = it },
                    label = { Text("Division name") },
                )
                Spacer(modifier = Modifier.size(20.dp))
                OutlinedTextField(
                    value = descriptionText,
                    onValueChange = { descriptionText = it },
                    label = { Text("description") }, maxLines = 2
                )
                Spacer(modifier = Modifier.size(20.dp))
                Text(text = "Choose your color theme:", color = MaterialTheme.colorScheme.onPrimary)
                Spacer(modifier = Modifier.size(5.dp))
                Row {
                    Box(Modifier.clickable { selectedThemeOption = ThemeOption.THEME_DEFAULT }) {
                        ThemeColorsIndicator(ThemeOption.THEME_DEFAULT)
                    }
                    //Spacer(modifier = Modifier.size(10.dp))
                    //Box(Modifier.clickable { selectedThemeOption = ThemeOption.THEME_GREEN }) {
                    //    ThemeColorsIndicator(ThemeOption.THEME_GREEN)
                    //}
                    //Spacer(modifier = Modifier.size(10.dp))
                    //Box(Modifier.clickable { selectedThemeOption = ThemeOption.THEME_PURPLE }) {
                    //    ThemeColorsIndicator(ThemeOption.THEME_PURPLE)
                    //}
                    Spacer(modifier = Modifier.size(10.dp))
                    Box(Modifier.clickable { selectedThemeOption = ThemeOption.THEME_BLUE }) {
                        ThemeColorsIndicator(ThemeOption.THEME_BLUE)
                    }
                }
            }

            /*Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 40.dp, end = 30.dp)
                    .size(140.dp)
                    .background(
                        color = MaterialTheme.colors.secondary.copy(alpha = .4f),
                        shape = CircleShape
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 10.dp, end = 50.dp)
                    .size(160.dp)
                    .background(
                        color = MaterialTheme.colors.secondary.copy(alpha = .4f),
                        shape = CircleShape
                    )
            )*/

            Box(
                modifier = Modifier
                    .padding(start = 20.dp, bottom = 20.dp)
                    .align(Alignment.BottomStart),
            ) {
                Text(
                    text = "Cancel",
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.Red)
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(topStart = 900.dp))
                    .background(
                        color = MaterialTheme.colorScheme.secondary.copy(alpha = .7f)
                    ),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    modifier = Modifier.padding(end = 20.dp, bottom = 20.dp),
                    text = "Save",
                    style = MaterialTheme.typography.labelMedium.copy(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
fun CreateDivisionScreenPreview() {
    CreateDivisionScreen()
}