package pt.joasvpereira.xorganizer.presentation.compose.common.holder.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility
import com.joasvpereira.dev.mokeupui.compose.screen.organizer.main.SimpleSpace
import pt.joasvpereira.xorganizer.presentation.compose.OverFlowRow
import pt.joasvpereira.coreui.shield.NameShield
import pt.joasvpereira.xorganizer.presentation.compose.division.SingleItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemHolder(
    onItemClick: () -> Unit,
    item: SingleItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable { onItemClick() },
        shape = RoundedCornerShape(15.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(vertical = 5.dp)
        ) {
            val (shieldRef,
                nameRef,
                statusRef,
                descriptionRef,
                tagContainerRef) = createRefs()

            NameShield(
                text = item.title,
                borderSize = 0.dp,
                textStyle = MaterialTheme.typography.labelSmall,
                backgroundColor = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.constrainAs(shieldRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    width = Dimension.value(25.dp)
                    height = Dimension.value(25.dp)
                }
            )

            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.constrainAs(nameRef) {
                    top.linkTo(shieldRef.top)
                    bottom.linkTo(shieldRef.bottom)
                    start.linkTo(shieldRef.end, 10.dp)
                    end.linkTo(statusRef.start)
                    width = Dimension.fillToConstraints
                }
            )

            Text(
                text = item.description ?: "",
                fontWeight = FontWeight.Light,
                modifier = Modifier.constrainAs(descriptionRef) {
                    top.linkTo(shieldRef.bottom, 5.dp, 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                    visibility = if (item.description != null) Visibility.Visible else Visibility.Gone
                }
            )

            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary.copy(alpha = .4f),
                        shape = RoundedCornerShape(200.dp)
                    )
                    .padding(5.dp)
                    .constrainAs(statusRef) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    },
            ) {
                Text(
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = if(item.isUsed) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 11.sp,
                    text = if (item.isUsed) {
                        "Used"
                    } else {
                        "Unused"
                    }
                )
            }


            Row(modifier = Modifier.constrainAs(tagContainerRef) {
                top.linkTo(descriptionRef.bottom, 10.dp, 10.dp)
                bottom.linkTo(parent.bottom, 5.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
                height = Dimension.wrapContent
            }) {
                Text(text = "Tags:", style = MaterialTheme.typography.labelSmall)
                SimpleSpace(size = 5.dp)
                if (item.tags.isNotEmpty())
                    OverFlowRow(spaceBetween = 5.dp,
                        overFlowBadge = {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    ) {
                        item.tags.forEach {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall,
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.tertiary,
                                        shape = RoundedCornerShape(5.dp)
                                    )
                                    .padding(horizontal = 2.dp)
                                    .padding(vertical = 1.dp)
                            )
                        }
                    }
            }
        }
    }
}

@Preview
@Composable
private fun ItemHolderPreview() {
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
        ItemHolder(
            onItemClick = {},
            item = SingleItem(
                title = "w W",
                description = null,
                tags = listOf(),
                isUsed = false
            )
        )
        ItemHolder(
            onItemClick = {},
            item = SingleItem(
                title = "test",
                description = "dsadasd",
                tags = listOf(),
                isUsed = false
            )
        )
        ItemHolder(
            onItemClick = {},
            item = SingleItem(
                title = "test",
                description = null,
                tags = listOf("sad", "dfdfd"),
                isUsed = false
            )
        )
        ItemHolder(
            onItemClick = {},
            item = SingleItem(
                title = "test",
                description = "dsadasd",
                tags = listOf("sad", "dfdfd"),
                isUsed = false
            )
        )

        ItemHolder(
            onItemClick = {},
            item = SingleItem(
                title = "test",
                description = "dsadasd",
                tags = listOf(
                    "sad",
                    "dfdfd",
                    "sadsad",
                    "sad",
                    "dfdfd",
                    "sadsad",
                    "sad",
                    "dfdfd",
                    "sadsad",
                    "sad",
                    "dfdfd",
                    "sadsad",
                    "sad",
                    "dfdfd",
                    "sadsad"
                ),
                isUsed = false
            )
        )
    }
}