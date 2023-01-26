package com.joasvpereira.main.compose.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.IconAndCounter
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.IconData
import com.joasvpereira.main.domain.data.DashboardDivision
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.PlusSolid
import pt.joasvpereira.coreui.DynamicTheme
import pt.joasvpereira.main.R

@Composable
internal fun DashboardDivisionsSection(
    divisions: List<DashboardDivision>,
    onDivisionClick: (DashboardDivision) -> Unit,
    onAddNewItemClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Devisions:", style = MaterialTheme.typography.titleLarge)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                start = 0.dp,
                top = 16.dp,
                end = 0.dp,
                bottom = 16.dp
            )
        ) {
            items(divisions.size, key = { index -> divisions[index].id }) { index: Int ->
                DivisionItem(division = divisions[index]) {
                    onDivisionClick(divisions[index])
                }
            }
            item {
                AddActionItem(action = onAddNewItemClick)
            }
        }
    }
}


@Composable
private fun AddActionItem(
    modifier: Modifier = Modifier, action: () -> Unit
) {
    ItemContainer(
        modifier = modifier,
        clickAction = { action() },
        color = Color.LightGray
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(90.dp),
                imageVector = LineAwesomeIcons.PlusSolid,
                contentDescription = "",
                tint = Color.White
            )
        }
    }
}

@Composable
private fun DivisionItem2(
    modifier: Modifier = Modifier,
    division: DashboardDivision,
    action: () -> Unit
) {
    DynamicTheme(division.themeOption) {
        ItemContainer(
            modifier = modifier,
            clickAction = action,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                StyleShape(modifier = Modifier.align(Alignment.BottomEnd))
                Icon(
                    painter = painterResource(id = R.drawable.ic_small_options),
                    contentDescription = "Options for division ${division.name}",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 16.dp, end = 12.dp)
                        .clip(CircleShape)
                        .clickable { }
                )
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Icon(
                        painterResource(id = division.icon.resId),
                        contentDescription = "Icon of ${division.icon.name}",
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.size(16.dp))

                    Text(text = division.name, color = MaterialTheme.colorScheme.onPrimaryContainer)

                    Spacer(modifier = Modifier.size(4.dp))

                    division.description?.takeIf { it.isNotBlank() }?.run {
                        Text(
                            text = this,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    Row(
                        modifier = Modifier
                    ) {
                        IconAndCounter(
                            iconData = IconData(painterResource(R.drawable.ic_box), ""),
                            count = division.boxCount,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        IconAndCounter(
                            iconData = IconData(
                                painterResource(R.drawable.ic_baseline_build_24),
                                ""
                            ),
                            count = division.itemCount,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun DivisionItem(
    modifier: Modifier = Modifier,
    division: DashboardDivision,
    action: () -> Unit
) {
    DynamicTheme(division.themeOption) {
        ItemContainer(
            modifier = modifier,
            clickAction = action,
            color = MaterialTheme.colorScheme.primaryContainer
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                StyleShape(modifier = Modifier.align(Alignment.BottomEnd))
                Icon(
                    painter = painterResource(id = R.drawable.ic_small_options),
                    contentDescription = "Options for division ${division.name}",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 16.dp, end = 12.dp)
                        .clip(CircleShape)
                        .clickable { }
                )
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp, vertical = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.background(MaterialTheme.colorScheme.onPrimaryContainer, shape = CircleShape).padding(7.dp)) {
                            Icon(
                                painterResource(id = division.icon.resId),
                                contentDescription = "Icon of ${division.icon.name}",
                                modifier = Modifier.size(35.dp),
                                tint = MaterialTheme.colorScheme.primaryContainer
                            )
                        }

                        Spacer(modifier = Modifier.size(10.dp))

                        Text(
                            text = division.name,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontWeight = FontWeight.Bold,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    division.description?.takeIf { it.isNotBlank() }?.run {
                        Text(
                            text = this,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.size(8.dp))

                    Row(
                        modifier = Modifier
                    ) {
                        IconAndCounter(
                            iconData = IconData(painterResource(R.drawable.ic_box), ""),
                            count = division.boxCount,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                        Spacer(modifier = Modifier.size(8.dp))

                        IconAndCounter(
                            iconData = IconData(
                                painterResource(R.drawable.ic_baseline_build_24),
                                ""
                            ),
                            count = division.itemCount,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )

                    }
                }
            }
        }
    }
}

@Composable
private fun ItemContainer(
    modifier: Modifier = Modifier,
    clickAction: () -> Unit,
    color: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
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
        color = color
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
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 10.dp, end = 50.dp)
                .size(60.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .4f),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(90.dp)
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = .7f),
                    shape = RoundedCornerShape(topStart = 200.dp)
                )
        )
    }
}

@Preview
@Composable
private fun DashboardDivisionsSectionPreview() {
    DashboardDivisionsSection(divisions = PreviewData.divisions, onDivisionClick = {}, onAddNewItemClick = {})
}

@Preview
@Composable
private fun DivisionItemPreview() {
    DivisionItem(modifier = Modifier.fillMaxWidth(1f), division = PreviewData.divisions[0], action = {})
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