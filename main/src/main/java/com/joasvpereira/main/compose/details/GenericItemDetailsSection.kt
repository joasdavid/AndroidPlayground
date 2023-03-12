package com.joasvpereira.main.compose.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.lib.compose.spacer.SimpleSpace
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.main.R

@Composable
fun GenericItemDetailsSection(
    id: Int,
    name: String,
    description: String,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (id < 1) return
    GenericSectionContainer(
        headerText = stringResource(R.string.main_details_section_title),
        clickableIcon = HeaderClickableIcon(
            iconPainter = rememberVectorPainter(image = Icons.Default.Edit),
            onClick = onEditClick,
        ),
        modifier = modifier,
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                text = AnnotatedString(
                    text = stringResource(R.string.main_details_section_id_label),
                    spanStyle = SpanStyle(fontWeight = FontWeight.Bold),
                ).plus(
                    AnnotatedString(text = "$id"),
                ),
            )

            SimpleSpace(size = 5.dp)

            Text(
                text = AnnotatedString(
                    text = stringResource(R.string.main_details_section_name_label),
                    spanStyle = SpanStyle(fontWeight = FontWeight.Bold),
                ).plus(
                    AnnotatedString(text = name),
                ),
            )

            SimpleSpace(size = 5.dp)

            Text(
                text = AnnotatedString(
                    text = stringResource(R.string.main_details_section_description_label),
                    spanStyle = SpanStyle(fontWeight = FontWeight.Bold),
                ).plus(
                    AnnotatedString(text = description),
                ),
            )
        }
    }
}

@Preview
@Composable
private fun GenericItemDetailsSectionPreview() {
    DynamicTheme {
        GenericItemDetailsSection(id = 1, name = "Item XPTO", description = "This is my looooooooooooooooong description", onEditClick = {})
    }
}
