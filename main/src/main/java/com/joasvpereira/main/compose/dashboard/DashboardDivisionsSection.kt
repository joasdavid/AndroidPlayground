package com.joasvpereira.main.compose.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.joasvpereira.main.domain.data.DashboardDivision
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.PlusSolid
import pt.joasvpereira.coreui.counter.IconData
import pt.joasvpereira.coreui.counter.VerticalCounterWithIcon
import pt.joasvpereira.coreui.theme.DynamicTheme
import pt.joasvpereira.main.R

@Composable
internal fun DashboardDivisionsSection(
    divisions: List<DashboardDivision>,
    onDivisionClick: (DashboardDivision) -> Unit,
    onEditClick: (DashboardDivision) -> Unit,
    onAddNewItemClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Devisions:", style = MaterialTheme.typography.titleLarge)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 0.dp,
                top = 16.dp,
                end = 0.dp,
                bottom = 16.dp,
            ),
        ) {
            items(divisions.size, key = { index -> divisions[index].id }) { index: Int ->
                DivisionItem(
                    division = divisions[index],
                    onItemClicked = { onDivisionClick(divisions[index]) },
                    onEditClick = { onEditClick(divisions[index]) },
                )
            }
            item {
                AddActionItem(action = onAddNewItemClick)
            }
        }
    }
}

@Composable
private fun AddActionItem(
    modifier: Modifier = Modifier,
    action: () -> Unit,
) {
    ItemContainer(
        modifier = modifier,
        clickAction = { action() },
        color = Color.LightGray,
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                modifier = Modifier.size(90.dp),
                imageVector = LineAwesomeIcons.PlusSolid,
                contentDescription = "",
                tint = Color.White,
            )
        }
    }
}

@Composable
private fun DivisionItem(
    modifier: Modifier = Modifier,
    division: DashboardDivision,
    onItemClicked: () -> Unit,
    onEditClick: () -> Unit,
) {
    DynamicTheme(division.themeOption) {
        ItemContainer(
            modifier = modifier,
            clickAction = onItemClicked,
            color = MaterialTheme.colorScheme.primaryContainer,
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                StyleShape(modifier = Modifier.align(Alignment.BottomEnd))
                DivisionExtraOptions(
                    division = division,
                    onEditClick = onEditClick,
                )
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 16.dp),
                ) {
                    DivisionIconPlacement(division)

                    Spacer(modifier = Modifier.size(8.dp))

                    DivisionName(division)

                    Spacer(modifier = Modifier.size(5.dp))

                    DivisionCounters(division)
                }
            }
        }
    }
}

@Composable
private fun DivisionCounters(division: DashboardDivision) {
    Row(
        modifier = Modifier,
    ) {
        VerticalCounterWithIcon(
            iconData = IconData(painterResource(R.drawable.ic_box), ""),
            count = division.boxCount,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )

        Spacer(modifier = Modifier.size(8.dp))

        VerticalCounterWithIcon(
            iconData = IconData(
                painterResource(R.drawable.ic_baseline_build_24),
                "",
            ),
            count = division.itemCount,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    }
}

@Composable
private fun ColumnScope.DivisionName(division: DashboardDivision) {
    Text(
        text = division.name,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        modifier = Modifier.Companion.weight(1f),
    )
}

@Composable
private fun DivisionIconPlacement(division: DashboardDivision) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.onPrimaryContainer, shape = CircleShape)
                .padding(7.dp),
        ) {
            Icon(
                painterResource(id = division.icon.resId),
                contentDescription = "Icon of ${division.icon.name}",
                modifier = Modifier.size(35.dp),
                tint = MaterialTheme.colorScheme.primaryContainer,
            )
        }
    }
}

@Composable
private fun BoxScope.DivisionExtraOptions(
    division: DashboardDivision,
    onEditClick: () -> Unit,
) {
    Box(
        modifier = Modifier.Companion
            .align(Alignment.TopEnd)
            .size(48.dp)
            .padding(top = 16.dp, end = 12.dp)
            .clip(CircleShape)
            .clickable { onEditClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Edit division named ${division.name}",
        )
    }
}

@Composable
private fun ItemContainer(
    modifier: Modifier = Modifier,
    clickAction: () -> Unit,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit,
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(5.dp))
            .height(180.dp)
            .clickable {
                clickAction()
            }
            .then(modifier),
        color = color,
    ) {
        content()
    }
}

@Composable
private fun StyleShape(modifier: Modifier) {
    Box(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 40.dp, end = 30.dp)
                .size(40.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .4f),
                    shape = CircleShape,
                ),
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 50.dp)
                .size(60.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .4f),
                    shape = CircleShape,
                ),
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(90.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .7f),
                    shape = RoundedCornerShape(topStart = 200.dp),
                ),
        )
    }
}

@Preview
@Composable
private fun DashboardDivisionsSectionPreview() {
    DashboardDivisionsSection(divisions = PreviewData.divisions, onDivisionClick = {}, onAddNewItemClick = {}, onEditClick = {})
}

@Preview
@Composable
private fun DivisionItemPreview() {
    DivisionItem(modifier = Modifier.fillMaxWidth(1f), division = PreviewData.divisions[0], onItemClicked = {}, onEditClick = {})
}

@Preview
@Composable
private fun StyleShapePreview() {
    StyleShape(modifier = Modifier)
}

@Preview
@Composable
private fun AddActionItemPreview() {
    AddActionItem {}
}

@Preview
@Composable
private fun ItemContainerPreview() {
    ItemContainer(modifier = Modifier.fillMaxWidth(), clickAction = {}, content = {})
}
