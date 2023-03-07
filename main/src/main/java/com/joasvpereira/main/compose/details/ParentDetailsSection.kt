package com.joasvpereira.main.compose.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import com.joasvpereira.main.domain.data.ItemDetail
import com.joasvpereira.main.presentation.icons.DivisionIcons
import pt.joasvpereira.coreui.box.BoxImage
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.coreui.theme.ThemeOption

@Composable
fun ParentDetailsSection(
    division: ItemDetail.ParentDivision,
    box: ItemDetail.ParentBox?,
    modifier: Modifier = Modifier,
) {
    division.divisionIcon?.let { divisionIcon ->
        GenericSectionContainer(headerText = "Location", modifier = modifier) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .padding(vertical = 10.dp),
            ) {
                Row {
                    Icon(painter = painterResource(id = divisionIcon.resId), contentDescription = null, modifier = Modifier.size(24.dp))
                    SimpleSpace(size = 5.dp)
                    Text(text = division.name)
                }
                box?.let {
                    SimpleSpace(size = 15.dp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        SimpleSpace(size = 24.dp)
                        Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
                        SimpleSpace(size = 5.dp)
                        BoxImage(modifier = Modifier.size(20.dp))
                        SimpleSpace(size = 5.dp)
                        Text(text = it.name)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ParentDetailsSectionPreview() {
    DynamicTheme {
        ParentDetailsSection(
            division = ItemDetail.ParentDivision(
                id = 0,
                name = "Living Room",
                themeOption = ThemeOption.THEME_DEFAULT,
                divisionIcon = DivisionIcons.livingRoom,
            ),
            box = ItemDetail.ParentBox(id = 0, name = "My Box"),
        )
    }
}

@Preview
@Composable
private fun ParentDetailsSectionPreview_noBox() {
    DynamicTheme {
        ParentDetailsSection(
            division = ItemDetail.ParentDivision(
                id = 0,
                name = "Living Room",
                themeOption = ThemeOption.THEME_DEFAULT,
                divisionIcon = DivisionIcons.livingRoom,
            ),
            box = null,
        )
    }
}
